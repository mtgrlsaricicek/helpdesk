app.controller('LeftCtrl', function ($scope, $timeout, $mdSidenav) {
       $scope.close = function () {
         // Component lookup should always be available since we are not using `ng-if`
         $mdSidenav('left').close();
       };
     });