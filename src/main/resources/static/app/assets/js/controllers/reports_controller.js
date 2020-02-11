'use strict';

angular.module('myApp').controller('ReportsController', ['$window', '$timeout', '$scope', '$rootScope', 'ProductService', 'AuthService', 'uiGridConstants', 'UserService','ReportService', function ($window, $timeout, $scope, $rootScope, ProductService, AuthService, uiGridConstants, UserService,ReportService) {

    let reportsController = null;
    $scope.allGlData=null;


    $scope.findGLData = function () {
        ReportService.findGLData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allGlData = result.data;
            console.log($scope.allGlData);
            }
        });
    };


    $scope.getPurchaseData = function () {
        ReportService.getPurchaseData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.purchaseData.data = result.data;
                console.log($scope.allGlData);
            }
        });
    };
    $scope.getBranchSaleData = function () {
        ReportService.getBranchSaleData().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.branchSaleData.data = result.data;
                console.log($scope.allGlData);
            }
        });
    };
    $scope.purchaseAllColumns = [{name: 'product'},
        {name: 'purchaseDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'branch'},
    ];

    $scope.purchaseData = {
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
        columnDefs: $scope.purchaseAllColumns
    };


    $scope.purchaseData.onRegisterApi = function (gridApi) {
        $scope.purchaseData = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };

    $scope.branchSaleColumns = [{name: 'product'},
        {name: 'saleDate'},
        {name: 'unitPrice'},
        {name: 'quantity'},
        {name: 'totalAmount'},
        {name: 'branch'},
    ];

    $scope.branchSaleData = {
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
        columnDefs: $scope.branchSaleColumns
    };


    $scope.branchSaleData.onRegisterApi = function (gridApi) {
        $scope.branchSaleData = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;

    };
}]);
