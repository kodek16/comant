(function() {
  'use strict';

  angular
    .module('comantApp')
    .run(registerStateChangeHandler);

  registerStateChangeHandler.$inject = ['loginModal', 'session', '$rootScope', '$state'];

  function registerStateChangeHandler(loginModal, session, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart', (event, toState, toParams) => {

      //'logout' is a redirect state to 'welcome'
      if (toState.name == 'logout') {
        event.preventDefault();
        session.logout();
        $state.go('welcome');
        return;
      }

      //Prevent anonymous users from accessing protected states.
      let requireLogin = toState.data.requireLogin;

      if (requireLogin && typeof $rootScope.currentUser === 'undefined') {
        event.preventDefault();
        $state.go('welcome');

        session.waitForInitialization()
          .then(loginModalOrTrueIfLoggedIn)
          .then(() => {
            $state.go(toState.name, toParams)
          })
          .catch(() => $state.go('welcome'));
      }

      function loginModalOrTrueIfLoggedIn() {
        if (typeof $rootScope.currentUser !== 'undefined') {
          return true;
        } else {
          return loginModal();
        }
      }
    });
  }
})();