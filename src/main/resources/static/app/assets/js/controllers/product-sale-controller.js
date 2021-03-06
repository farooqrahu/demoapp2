'use strict';

angular.module('myApp').controller('ProductSaleController', ['$state','$stateParams','$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService', 'uiGridConstants', 'UserService', function ($state,$stateParams,$window, $timeout, $scope, $rootScope, ProductService, AuthService, uiGridConstants, UserService) {

    var productControllerVm = null;
    var edit = false;
    $scope.allProducts = null;
    $scope.quantity = 0;
    $scope.productSale = {id: '0', quantity: '0', branch: '', product: ''};
    $scope.productSale.newQuantity = 0;
    $scope.productCategoryDto = {productCategory: ""};
    $scope.productCompanyDto = {name: ""};
    $scope.product = {productCategoryDto: [], productCompanyDto: []};
    $scope.branchId = null;
    $scope.totalAmount = 0;
    $scope.quantityExceeded=null;
    $scope.today_date=new Date();

    $scope.branches = [];

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
    $scope.invoiceData= $stateParams.obj;
    $scope.subTotal= $stateParams.subTotal;
    $scope.invoiceNumber= $stateParams.invoiceNumber;

    $scope.redirectInvoicePage=function(){
        if($stateParams.obj==null){
            $state.go("home");
        }
    };

    $scope.selectProductCompany = function (name) {
        $scope.productCompanyDto.name = name;

    };
    $scope.selectProductCategory = function (name) {
        $scope.productCategoryDto.productCategory = name;

    };


    $scope.findAllProducts = function () {
        ProductService.findAllProducts().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allProductsDataUIGrid.data = result.data;
                UserService.findAllCustomerBranches().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        $scope.branches = result.data;
                    }
                });
            }
        });
    };

    $scope.setStatus = function (status) {
        $scope.product.isActive = status;
    };
    $scope.addProduct = function () {
        if ($scope.productCategory.value == 'Please Select' || $scope.productCompany.value == 'Please Select') {
            $scope.error = "Please Select Role";
        } else {
            if ($scope.productCategoryDto.productCategory != "") {
                $scope.product.productCategory = $scope.productCategoryDto;
            }
            if ($scope.productCompanyDto.name != "") {
                $scope.product.productCompany = $scope.productCompanyDto;
            }
            if ($scope.editUserBol) {
                $scope.editProduct($scope.product);
            } else {
                ProductService.addProduct($scope.product).then(function (result) {
                    if (result.status == "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.addProductForm.$setPristine();
                        $('#productModal').modal('hide');
                        $scope.allProductsDataUIGrid.data.push(result.data);
                        $rootScope.runSweetAlertMsg('Add New Product', 'Add new product successful !', 'success');
                    } else {
                        $rootScope.runSweetAlertMsg('Add New Product', result.data.message, 'error');
                    }
                });
            }

        }
    };

    $scope.addProductSale = function () {
        $scope.productSale.newQuantity = parseInt($scope.productSale.newQuantity);
        $scope.productSale.product = $scope.product.id;
        console.log($scope.productSale);
        ProductService.addProductStock($scope.productSale).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                $rootScope.runSweetAlertMsg('Product ', 'Add product stock successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Product', result.data.message, 'error');
            }
        });
    };

    $scope.clearData = function () {
        $scope.product = null;
        $scope.editUserBol = false;


    };

    $scope.saleProductSaleToBranch= function () {
        $scope.productSale.product = $scope.product.id;
        if(!$scope.productSale.branch){
            $rootScope.runSweetAlertMsg('Sale Product', 'Please Select Branch', 'error');
        return null;
        }
        ProductService.saleProductStockToBranch($scope.productSale).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $scope.confirmPassword = null;
                $('#productModal').modal('hide');
                $rootScope.runSweetAlertMsg('Product', 'Add product stock successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Product', result.data.message, 'error');
            }
        });
    };



    $scope.deleteSelected = function () {
        console.log($scope.allProductsDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };


    $scope.listAllColumns = [{name: 'name'},
        {name: 'model'},
        {name: 'productCategory.productCategory', displayName: 'Category'},
        {name: 'productCompany.name', displayName: 'Company'},
        {
            name: 'edit',
            displayName: 'Edit Sale',
            cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
        }
    ];

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
        columnDefs: $scope.listAllColumns
    };


    $scope.allProductsDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allProductsDataUIGrid = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.product = entity;
        $scope.productCategory.value = $scope.product.productCategory.productCategory;
        $scope.productCompany.value = $scope.product.productCompany.name;
        $scope.showSaleBranchWise();
        /*if ($scope.product.isActive) {
            $("#active").prop("checked", true);
        } else {
            $("#inactive").prop("checked", true);
        }*/
        $scope.productSale.newQuantity= 0;
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
                $scope.deleteProduct(entity);
            }
        });
    };

    $scope.editProduct = function (entity) {
        ProductService.editProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back
                $('#productModal').modal('hide');
                $scope.editUserBol = false;
                $rootScope.runSweetAlertMsg('Edit Product', 'Product Edited Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Edit Product', 'Edit Product failed!', 'error');
            }
        });
    };

    $scope.deleteProduct = function (entity) {
        ProductService.deleteProduct(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allProductsDataUIGrid.data.indexOf(entity);
                $scope.allProductsDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete Product', 'Product Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete Product', 'Delete Product failed!', 'error');
            }
        });

    };
    $scope.showSaleBranchWise = function (branch) {
        var data = {'branch': 1, 'product': $scope.product.id};
        ProductService.getStockBranchWise(data).then(function (result) {
            if (result.status == "201") {
                $scope.productSale = result.data;
                $scope.productSale.branch=null;
            }
        });
    };
    /*   $scope.showSaleBranchWise = function (branch) {
           if (branch == null || $scope.product.id == null) {
               $scope.productSale = {quantity: 0};
               return null;
           }
           var data = {'branch': branch, 'product': $scope.product.id};
           ProductService.getSaleBranchWise(data).then(function (result) {
               if (result.status == "201") {
                   $scope.productSale = result.data;
               }
           });
       };
    */
    $scope.calculateTotalSalePurAmount = function () {
        if ($scope.productSale.newQuantity) {
             $scope.productSale.newTotalSaleAmount = $scope.productSale.salePrice *  $scope.productSale.newQuantity;
            $scope.productSale.newTotalPurchaseAmount = $scope.productSale.purchasePrice *  $scope.productSale.newQuantity;
        }
    };

    $scope.calculateTotalSaleAmount = function () {
        if ($scope.productSale.newQuantity) {
            let availableQuantity = parseInt($scope.productSale.quantity);
            let newQuantity = parseInt($scope.productSale.newQuantity);
            if (newQuantity > availableQuantity) {
                $scope.productSale.newQuantity = 0;
                $scope.quantityExceeded="Quantity entered not available in stock";
            }else{
                $scope.quantityExceeded=null;

            }
            $scope.productSale.newTotalSaleAmount =  parseInt($scope.productSale.salePrice) * newQuantity;
        }
    };



}]);
