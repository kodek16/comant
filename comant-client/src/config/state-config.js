(function() {
  'use strict';

  angular
    .module('comantApp')
    .config(stateConfig);

  stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

  function stateConfig($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/courses');

    $stateProvider
      .state('welcome', {
        url: '/welcome',
        templateUrl: 'blocks/welcome-page/welcome-page.html',
        data: {
          requireLogin: false
        }
      })
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
      .state('course', {
        url: '/course/{courseId}',
        abstract: true,
        templateUrl: 'blocks/course-view/course-view.html',
        controller: 'CourseViewController',
        controllerAs: 'courseView',
        data: {
          requireLogin: true
        }
      })
      .state('course.classes', {
        url: '/classes',
        templateUrl: 'blocks/classes-list/classes-list.html',
        controller: 'ClassesListController',
        controllerAs: 'classesList'
      })
      .state('course.students', {
        url: '/students',
        templateUrl: 'blocks/listeners-list/listeners-list.html',
        controller: 'ListenersListController',
        controllerAs: 'listenersList'
      })
      .state('logout', {
        url: '/logout',
        data: {
          requireLogin: false
        }
      });
  }
})();
