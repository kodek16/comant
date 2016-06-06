(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('LectureViewController', LectureViewController);

  LectureViewController.$inject = ['Lecture', '$stateParams'];
  
  function LectureViewController(Lecture, $stateParams) {
    let vm = this;
    
    vm.lecture = null;
    
    init();
    
    function init() {
      vm.lecture = Lecture.get({
        id: $stateParams.lectureId
      });
    }
  }
})();