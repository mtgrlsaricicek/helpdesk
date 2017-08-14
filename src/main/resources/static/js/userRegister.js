app.controller('userRegisterController',['$scope','sidenavCloseService',
                        function($scope,sidenavCloseService) {
    sidenavCloseService.closeIfOpen();
    $scope.message = 'This page is in the process of preparation';
}]);