(function() {
  angular
    .module('comantApp')
    .filter('dateComponents', dateComponents);

  dateComponents.$inject = ['dateFilter'];

  function dateComponents(dateFilter) {
    return input => {
      let date = new Date(input[0], input[1] - 1, input[2]);
      return dateFilter(date, 'mediumDate');
    }
  }
})();