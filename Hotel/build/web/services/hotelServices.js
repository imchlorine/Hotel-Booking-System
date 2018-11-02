var app = angular.module('hotel', []);
app.controller('hotelServices', function ($scope, $http) {
   
    $http({
        
        method: "GET",
        dataType: 'jsonp',
        url: "http://localhost:62533/Webservices/webresources/transaction/getAllTransType"
    }).then(function mySuccess(response) {
        $scope.transTypeList = response.data;
    }, function myError(response) {
        $scope.transTypeList = "";
    });
});