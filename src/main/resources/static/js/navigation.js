app.controller('navigation',['$rootScope','$scope','$http','$location',
                             '$mdSidenav','$timeout','$window','sidenavCloseService',
                function($rootScope,$scope,
                        $http,$location,$mdSidenav,$timeout,$window,sidenavCloseService) {

        sidenavCloseService.closeIfOpen();

      var authenticate = function(callback) {
          $http.get('user/getCurrentUser').success(function(data) {
            if (data.name) {
              $rootScope.authenticated = true;
              $rootScope.name = data.name;
              $rootScope.surname = data.surname;
            } else {
              $rootScope.authenticated = false;
            }
            callback && callback();
          }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
          });
        }

      $scope.credentials = {};
      authenticate();
      $scope.login = function() {
         $http.post('login', $.param($scope.credentials), {
           headers : {
             "content-type" : "application/x-www-form-urlencoded"
           }
         }).success(function(data) {
            authenticate(function() {
              if ($rootScope.authenticated) {
                $scope.error = false;
                $window.location = "http://localhost:8080/helpdesk/";
              } else {
                $location.path("/login");
                $scope.error = true;
              }
            });
         }).error(function(data) {
           $location.path("/login");
           $scope.error = true;
           $rootScope.authenticated = false;
         })
       }

       $scope.logout = function() {
              sidenavCloseService.closeIfOpen()
              $http.post('logout').success(function() {
                $rootScope.authenticated = false;
                $window.location = "http://localhost:8080/helpdesk/"
              }).error(function(data) {
                $rootScope.authenticated = false;
                $window.location = "http://localhost:8080/helpdesk/"
              });
            }

            $scope.toggleLeft = buildDelayedToggler('left');

                   /**
                    * Supplies a function that will continue to operate until the
                    * time is up.
                    */
                   function debounce(func, wait, context) {
                     var timer;

                     return function debounced() {
                       var context = $scope,
                           args = Array.prototype.slice.call(arguments);
                       $timeout.cancel(timer);
                       timer = $timeout(function() {
                         timer = undefined;
                         func.apply(context, args);
                       }, wait || 10);
                     };
                   }

                   /**
                    * Build handler to open/close a SideNav; when animation finishes
                    * report completion in console
                    */
                   function buildDelayedToggler(navID) {
                     return debounce(function() {
                       // Component lookup should always be available since we are not using `ng-if`
                       $mdSidenav(navID)
                         .toggle();
                     }, 200);
                   }

                   function buildToggler(navID) {
                     return function() {
                       // Component lookup should always be available since we are not using `ng-if`
                       $mdSidenav(navID)
                         .toggle();
                     };
                   }

       $scope.go = function(path){
            sidenavCloseService.closeIfOpen();
            $location.path(path);
       }


}]);