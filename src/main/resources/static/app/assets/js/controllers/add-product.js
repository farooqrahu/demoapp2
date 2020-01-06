angular.module('myApp')

    .controller('AddProductController', function ($scope, $http, $rootScope, $timeout) {

        $(document).ready(function() {
            $.fn.select2.defaults.set("minimumResultsForSearch","Infinity");
            $(".select--filter").select2({
                minimumResultsForSearch: 2
            }).on('change',function(){$('.select--filter--value').text($(".select--filter").val());});
            $(".select--no-filter").select2();
        }).on('change',function(){$('.select--no-filter--value').text($(".select--no-filter").val());});


        $('#file').on('change', function() {
            document.getElementById("form").submit();
        });

        $('#date').mask("9999");
        $('#duration').mask("99:99");

        $scope.addProduct = function () {

           /* if( document.getElementById("file").files.length === 0 ){
                $scope.message = 'Please select file!';
                $timeout(function(){$scope.message = '';}, 3000);

            } else {*/

                $http.post('http://localhost:8080/product/add', $scope.product).success(function () {
                    $scope.message = "Product added successfully!";
                    $timeout(function(){$scope.message = '';}, 3000);
                    $scope.product.name = '';
                    $scope.product.author = null;
                    $scope.product.duration = '';
                    $scope.product.date = '';
                    $scope.product.album = '';
                    $('#file').val('');
                }).error(function () {
                    $scope.message = "Oops check form please!";
                    $timeout(function(){$scope.message = '';}, 3000);
                });
            };

        // };


        $scope.createAuthor = function () {
            $scope.addAuthor = true;
        };

        $scope.create = function () {

            $http.post('http://localhost:8080/author/add', $scope.author).success(function () {
                $scope.message = "Author added successfully!";
                $timeout(function(){$scope.message = '';}, 3000);
                $scope.author.name = '';
                $scope.addAuthor = null;
                $rootScope.loadAuthors();
            }).error(function () {
                $scope.message = "Oops check form please!";
                $timeout(function(){$scope.message = '';}, 3000);
            });
        };

    });
