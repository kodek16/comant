(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('authInterceptor', authInterceptor)
    .config(addAuthInterceptor);

  authInterceptor.$inject = ['session', '$timeout', '$q', '$injector'];

  function authInterceptor(session, $timeout, $q, $injector) {
    let loginModal, $http, $state;

    // Circular dependencies workaround.
    $timeout(function () {
      loginModal = $injector.get('loginModal');
      $http = $injector.get('$http');
      $state = $injector.get('$state');
    });

    return { request, responseError };

    // Adds 'Authorization' header to each request if we are logged in.
    function request(config) {
      let token = session.getAuthToken();
      if (token) {
        config.headers.Authorization = 'Bearer ' + token;
      }

      return config;
    }

    // Retries login when a 401 response is received.
    function responseError(rejection) {
      if (rejection.status !== 401) {
        return $q.reject(rejection);
      }

      let deferred = $q.defer();

      loginModal()
        .then(() => deferred.resolve($http(rejection.config)))
        .catch(() => {
          $state.go('welcome');
          deferred.reject(rejection);
        });

      return deferred.promise;
    }
  }

  addAuthInterceptor.$inject = ['$httpProvider'];

  function addAuthInterceptor($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
  }
})();
