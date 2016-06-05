(function() {
  'use strict';

  angular
    .module('comantApp')
    .filter('timeInterval', timeInterval);

  function timeInterval() {
    return function(times) {
      let start = times[0];
      let end = times[1];

      if (moment(start).startOf('day').isSame(moment(end).startOf('day'))) {
        return `${moment(start).format('ll')}: ${moment(start).format('LT')} - ${moment(end).format('LT')}`;
      } else {
        return `${moment(start).format('lll')} - ${moment(end).format('lll')}`;
      }
    }
  }
})();