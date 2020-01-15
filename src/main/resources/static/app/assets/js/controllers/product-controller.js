'use strict';

angular.module('myApp').controller('ProductController', ['$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService','uiGridConstants', function ($window, $timeout, $scope, $rootScope, ProductService, AuthService,uiGridConstants) {

    var productControllerVm=null;
    var edit = false;
    $scope.allProducts = null;
    $scope.user = {productCategory: []};
    $scope.error = "";
    $scope.editUserBol = false;
    $scope.productCategory = {
        "type": "select",
        "name": "productCategory",
        "value": "Please Select",
        "values": ["Please Select"]
    };



    $scope.findAllProducts = function () {
        ProductService.findAllProducts().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allProductsDataUIGrid.data = result.data;
                ProductService.findAllProductCategory().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        angular.forEach(result.data, function (value, key) {
                            $scope.productCategory.values.push(value.productCategory);
                        });
                    }
                });

            }
        });
    };

    $scope.setStatus = function (status) {
        $scope.user.isActive = status;
    };
    $scope.addProduct = function () {
        if ($scope.productCategory.value == 'Please Select') {
            $scope.error = "Please Select Role";
        } else {
            $scope.user.role = $scope.productCategory.value;
            if ($scope.editUserBol) {
                $scope.editProduct($scope.user);
            } else {
                ProductService.addProduct($scope.user).then(function (result) {
                    if (result.status == "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.addProductForm.$setPristine();
                        $('#userModal').modal('hide');
                        $scope.allProductsDataUIGrid.data.push(result.data);
                        $rootScope.runSweetAlertMsg('Add New User', 'User Registration successful !', 'success');
                    } else {
                        $rootScope.runSweetAlertMsg('Add New User', result.data.message, 'error');
                    }
                });
            }

        }
    };

    $scope.clearData = function () {
        $scope.user = null;
        $scope.editUserBol = false;


    };

    $scope.deleteSelected = function () {
        console.log($scope.allProductsDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


    $scope.allProductsDataUIGrid = {
        rowHeight: 40,
        enableFiltering: true,
        enableGridMenu: true,
        enableRowSelection: false,
        enableSelectAll: true,
        selectionRowHeaderWidth: 50,
        infiniteScrollDown: true,
        enableColumnResizing: true,
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 25,


        enableFullRowSelection: true,
        showGridFooter: true,
        enableHorizontalScrollbar: uiGridConstants.scrollbars.WHEN_NEEDED, // Here!
        enableVerticalScrollbar: uiGridConstants.scrollbars.WHEN_NEEDED,
        columnDefs: [
            {name:'name',width:"100"},
            {name:'releaseDate',width:"150"},
            {name:'display',width:"150"},
            {name:'dimension',width:"150"},
            {name:'weight',width:"150"},
            {name:'os',width:"150"},
            {name:'memory',width:"150"},
            {name:'cameras',width:"150"},
            {name:'battery',width:"150"},
            {name:'colors',width:"150"},
            {name:'osVersion',width:"150"},
            {name:'price',width:"150"},
            {
                name: 'edit',width:"150",
                displayName: 'Edit',
                cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
            },
            {
                name: 'delete',width:"150",
                displayName: 'Delete',
                cellTemplate: '<button id="deleteBtn" type="button" class="btn btn-sm btn-danger mdi mdi-pen-remove red " ng-click="grid.appScope.remove(row.entity)" ></button>'
            }
        ]
    };


    $scope.allProductsDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allProductsDataUIGrid = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope, function (row) {
            var msg = 'row selected ' + row.isSelected;

        });

        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
            var msg = 'rows changed ' + rows.length;

        });
    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.user = entity;
        $scope.productCategory.value = $scope.user.productCategory[0].name;
        if ($scope.user.isActive) {
            $("#active").prop("checked", true);
        } else {
            $("#inactive").prop("checked", true);
        }

        $scope.editUserBol = true;
        $('#userModal').modal('show');
    };

    $scope.remove = function (entity) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                $scope.deleteUser(entity);
            }
        });
    };

    $scope.editProduct = function (entity) {
        ProductService.editProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $('#userModal').modal('hide');
                $scope.editUserBol = false;
                $rootScope.runSweetAlertMsg('Edit User', 'User Edited Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Edit User', 'Edit User failed!', 'error');
            }
        });
    };

    $scope.deleteUser = function (entity) {
        ProductService.deleteUser(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allProductsDataUIGrid.data.indexOf(entity);
                $scope.allProductsDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete User', 'User Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete User', 'Delete User failed!', 'error');
            }
        });

    };


}]);
