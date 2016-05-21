(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('PageController', PageController);

  PageController.$inject = ['$http'];

  function PageController($http) {
    let vm = this;

    vm.siteName = "";

    init();

    function init() {
      $http.get('api/site-settings')
        .then(loadSiteSettings)
    }

    function loadSiteSettings(response) {
      vm.siteName = response.data.name;
    }
  }
})();
