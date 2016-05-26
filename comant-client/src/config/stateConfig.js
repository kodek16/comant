(function() {
  'use strict';

  angular
    .module('comantApp')
    .config(stateConfig);

  stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

  function stateConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/courses');

    $stateProvider
      .state('courses', {
        url: '/courses',
        templateUrl: 'blocks/courses-overview/courses-overview.html',
        controller: 'CoursesOverviewController',
        controllerAs: 'coursesOverview',
        data: {
          requireLogin: true
        }
      })
      .state('courses.ongoing', {
        url: '/ongoing'
      })
      .state('courses.past', {
        url: '/past'
      })
      .state('courses.new', {
        url: '/new'
      })
      .state('welcome', {
        url: '/welcome',
        templateUrl: 'blocks/welcome-page/welcome-page.html',
        data: {
          requireLogin: false
        }
      })
      .state('logout', {
        url: '/logout',
        data: {
          requireLogin: false
        }
      });
  }
})();
