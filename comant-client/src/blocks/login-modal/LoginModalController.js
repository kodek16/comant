(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('LoginModalController', LoginModalController);

  LoginModalController.$inject = ['session', 'toastr', '$scope'];

  function LoginModalController(session, toastr, $scope) {
    let vm = this;

    vm.cancel = $scope.$dismiss;

    vm.submit = function(username, password) {
      session.authenticate(username, password)
        .then($scope.$close)
        .catch(() => {
          toastr.error('Wrong username or password.', 'Error');
        });
    }
  }
})();