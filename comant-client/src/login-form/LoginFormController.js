(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('LoginFormController', LoginFormController);

  LoginFormController.$inject = ['localStorageService', 'toastr', '$http', '$log'];

  function LoginFormController(localStorageService, toastr, $http, $log) {
    let vm = this;

    vm.username = "";
    vm.password = "";

    vm.login = login;

    function login() {
      let credentials = {
        "username": vm.username,
        "password": vm.password
      };
      $http.post('/api/login', credentials)
        .then(saveToken, processError);
    }

    function saveToken(response) {
      localStorageService.set('authToken', response.data.token);
    }

    function processError(response) {
      toastr.error('Wrong username or password', 'Error');
      $log.error(`[LoginFormController] Authentication error: ${response.status} ${response.data.message}`);
    }
  }
})();
