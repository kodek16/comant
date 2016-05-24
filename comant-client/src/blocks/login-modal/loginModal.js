(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('loginModal', loginModal);

  loginModal.$inject = ['$modal'];

  function loginModal($modal) {
    return function() {
      let instance = $modal.open({
        templateUrl: 'blocks/login-modal/login-modal.html',
        controller: 'LoginModalController as loginModal'
      });

      return instance.result;
    }
  }
})();
