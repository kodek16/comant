(function() {
  angular
    .module('comantApp')
    .factory('session', session);

  session.$inject = ['localStorageService', '$injector', '$rootScope', '$timeout', '$q'];

  function session(localStorageService, $injector, $rootScope, $timeout, $q) {
    let $http;

    //Introduces circular dependencies otherwise
    $timeout(() => {
      $http = $injector.get('$http');
    });

    let initializationDeferred = $q.defer();

    return {
      authenticate,
      getAuthToken,
      logout,
      initializationComplete,
      waitForInitialization
    };

    function authenticate(username, password) {
      return $http.post('/api/login', { username, password })
        .then(setCurrentUser);

      function setCurrentUser(response) {
        localStorageService.set('authToken', response.data.token);
        return $http.get('/api/users/me')
          .then(response => {
            $rootScope.currentUser = response.data;
          });
      }
    }

    function logout() {
      localStorageService.remove('authToken');
      delete $rootScope.currentUser;
    }

    function getAuthToken() {
      return localStorageService.get('authToken');
    }

    function initializationComplete() {
      initializationDeferred.resolve();
    }

    function waitForInitialization() {
      return initializationDeferred.promise;
    }
  }
})();
