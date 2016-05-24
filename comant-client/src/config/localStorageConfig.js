(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .config(localStorageConfig);

  localStorageConfig.$inject = ['localStorageServiceProvider'];

  function localStorageConfig(localStorageServiceProvider) {
    localStorageServiceProvider.setPrefix('comantApp');
  }
})();
