(function() {
  'use strict';

  angular
    .module('comantApp')
    .run(loadSiteSettings);

  loadSiteSettings.$inject = ['$http', '$rootScope'];

  function loadSiteSettings($http, $rootScope) {
    $http.get('api/site-settings')
      .then(response => {
        $rootScope.siteName = response.data.name;
      });
  }
})();