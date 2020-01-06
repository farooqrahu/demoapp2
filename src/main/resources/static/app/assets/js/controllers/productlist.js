angular.module('myApp')

.controller('ProductlistController', function ($scope, $http, $rootScope, AuthService) {

    $scope.user = AuthService.user;

    $http.get('http://localhost:8080/productList/' + $scope.user.principal.id).then(function (response) {
        $scope.products = response.data;
    });



    $scope.deleteProductFromProductlist = function (product) {
        var idx = $scope.products.indexOf(product);
        $http.delete('http://localhost:8080/productList/delete/' + product.id).then($scope.products.splice(idx, 1));
    };

    // $scope.playProduct = function () {
    //     $http.get('http://localhost:8080/play').then(function (responce) {
    //         $scope.productsPlay = responce.data;
    //     });
    // };

    $scope.productsDetails = function (product) {

        $rootScope.productsId = product.id;
        $rootScope.productsName = product.name;
        $rootScope.productsAuthor = product.author.name;
        $rootScope.productsDuration = product.duration;
        $rootScope.productsDate = product.date;
        $rootScope.productsAlbum = product.album;

    };


});
