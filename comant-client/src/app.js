(function () {
  'use strict';

  angular.module('comantApp', [
    'ngRoute',
    'mm.foundation',
    'LocalStorageModule',
    'ngAnimate',
    'toastr'
  ]);

  angular
    .module('comantApp')
    .config(routeConfig)
    .config(localStorageConfig);

  routeConfig.$inject = ['$routeProvider'];

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/login', {
        templateUrl: 'login-form/login-form.html',
        controller: 'LoginFormController',
        controllerAs: 'loginForm'
      })
      .otherwise({
        redirectTo: '/login'
      });
  }

  localStorageConfig.$inject = ['localStorageServiceProvider'];

  function localStorageConfig(localStorageServiceProvider) {
    localStorageServiceProvider.setPrefix('comantApp');
  }
})();