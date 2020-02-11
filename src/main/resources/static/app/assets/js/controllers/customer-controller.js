'use strict';

angular.module('myApp').controller('CustomerController', ['$window', '$timeout', '$scope', '$rootScope', 'CustomerService', 'AuthService', 'uiGridConstants', function ($window, $timeout, $scope, $rootScope, CustomerService, AuthService, uiGridConstants) {

    var customerControllerVm = null;
    var edit = false;
    $scope.allCustomers = null;

    $scope.productCategoryDto = {productCategory: ""};
    $scope.productCompanyDto = {name: ""};
    $scope.product = {productCategoryDto: [],productCompanyDto: []};

    $scope.error = "";
    $scope.editUserBol = false;
    $scope.productCategory = {
        "type": "select",
        "name": "productCategory",
        "value": "Please Select",
        "values": ["Please Select"]
    };
    $scope.productCompany = {
        "type": "select",
        "name": "productCompany",
        "value": "Please Select",
        "values": ["Please Select"]
    };

    $scope.selectCustomerCompany = function (name) {
        $scope.productCompanyDto.name = name;

    };
    $scope.selectCustomerCategory = function (name) {
        $scope.productCategoryDto.productCategory = name;

    };


    $scope.findAllCustomers = function () {
        CustomerService.findAllCustomers().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allCustomersDataUIGrid.data = result.data;
                CustomerService.findAllCustomerCategories().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        angular.forEach(result.data, function (value, key) {
                            $scope.productCategory.values.push(value.productCategory);
                        });
                        CustomerService.findAllCustomerCompanies().then(function (result) {
                            if (result.status == "200") { // check if we get the data back
                                angular.forEach(result.data, function (value, key) {
                                    $scope.productCompany.values.push(value.name);
                                });
                            }
                        });
                    }
                });

            }
        });
    };

    $scope.setStatus = function (status) {
        $scope.product.isActive = status;
    };
    $scope.addCustomer = function () {
        if ($scope.productCategory.value == 'Please Select' || $scope.productCompany.value == 'Please Select') {
            $scope.error = "Please Select Role";
        } else {
            if($scope.productCategoryDto.productCategory!=""){
                $scope.product.productCategory = $scope.productCategoryDto;
            }
            if($scope.productCompanyDto.name!=""){
                $scope.product.productCompany = $scope.productCompanyDto;
            }
            if ($scope.editUserBol) {
                $scope.editCustomer($scope.product);
            } else {
                CustomerService.addCustomer($scope.product).then(function (result) {
                    if (result.status == "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.addCustomerForm.$setPristine();
                        $('#productModal').modal('hide');
                        $scope.allCustomersDataUIGrid.data.push(result.data);
                        $rootScope.runSweetAlertMsg('Add New Customer', 'Add new product successful !', 'success');
                    } else {
                        $rootScope.runSweetAlertMsg('Add New Customer', result.data.message, 'error');
                    }
                });
            }

        }
    };

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.deleteSelected = function () {
        console.log($scope.allCustomersDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


$scope.listAllColumns= [{name: 'name', width: "100"},
    {name: 'releaseDate', width: "150"},
    {name: 'display', width: "150"},
    {name: 'dimension', width: "150"},
    {name: 'weight', width: "150"},
    {name: 'os', width: "150"},
    {name: 'osVersion', width: "150"},
    {name: 'memory', width: "150"},
    {name: 'cameras', width: "150"},
    {name: 'battery', width: "150"},
    {name: 'colors', width: "150"},
    {name: 'price', width: "150"},
    {
        name: 'edit', width: "150",
        displayName: 'Edit',
        cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
    },
    {
        name: 'delete', width: "150",
        displayName: 'Delete',
        cellTemplate: '<button id="deleteBtn" type="button" class="btn btn-sm btn-danger mdi mdi-pen-remove red " ng-click="grid.appScope.remove(row.entity)" ></button>'
    }];

    $scope.allCustomersDataUIGrid = {
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
        columnDefs: $scope.listAllColumns
    };


    $scope.allCustomersDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allCustomersDataUIGrid = gridApi;
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
        $scope.product = entity;
        $scope.productCategory.value = $scope.product.productCategory.productCategory;
        $scope.productCompany.value = $scope.product.productCompany.name;

        /*if ($scope.product.isActive) {
            $("#active").prop("checked", true);
        } else {
            $("#inactive").prop("checked", true);
        }*/

        $scope.editUserBol = true;
        $('#productModal').modal('show');
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
                $scope.deleteCustomer(entity);
            }
        });
    };

    $scope.editCustomer = function (entity) {
        CustomerService.editCustomer(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $('#productModal').modal('hide');
                $scope.editUserBol = false;
                $rootScope.runSweetAlertMsg('Edit Customer', 'Customer Edited Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Edit Customer', 'Edit Customer failed!', 'error');
            }
        });
    };

    $scope.deleteCustomer = function (entity) {
        CustomerService.deleteCustomer(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allCustomersDataUIGrid.data.indexOf(entity);
                $scope.allCustomersDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete Customer', 'Customer Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete Customer', 'Delete Customer failed!', 'error');
            }
        });

    };


}]);
