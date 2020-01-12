'use strict';

angular.module('myApp').controller('UsersController', ['$window', '$timeout', '$scope', '$rootScope', 'UserService', function ($window, $timeout, $scope, $rootScope, UserService) {
    var edit = false;
    this.userControllerVm = null;
    $scope.allUsers = null;
    $scope.user = null;
    $scope.error="";
    $scope.editUserBol = false;
    $scope.findAllUsers = function () {
        UserService.findAllUsers().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allUsersDataUIGrid.data = result.data;
            }
        });
    };

    $scope.registerUser = function () {
        if($scope.roles.value=='Please Select'){
            $scope.error="Please Select Role";
        }else{
            $scope.user.role= $scope.roles.value;
            if ($scope.editUserBol) {
                $scope.editUser($scope.user);
            } else {
                UserService.registerUser($scope.user).then(function (result) {
                    if (result.status== "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.registerUserForm.$setPristine();
                        $('#userModal').modal('hide');
                        $scope.allUsersDataUIGrid.data.push($scope.user);
                        $rootScope.runSweetAlertMsg('Add New User', 'User Registration successful !', 'success');
                    }else{
                        $rootScope.runSweetAlertMsg('Add New User', result.data.message, 'error');
                    }
                });
            }

        }
    };

    $scope.clearData= function () {
    $scope.user=null;
    $scope.editUserBol = false;
    };

    $scope.deleteSelected = function() {
        console.log($scope.allUsersDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
    };
    $scope.roles = {
        "type": "select",
        "name": "role",
        "value": "Please Select",
        "values": ["Please Select", "Admin", "Shope Keeper", "Entry Operator"]
    };

    $scope.allUsersDataUIGrid = {
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

        columnDefs: [
            {name: 'name'},
            {name: 'username'},
            {name: 'role'},
            {
                name: 'edit',
                displayName: 'Edit',
                cellTemplate: '<button id="editBtn" type="button" class="btn btn-sm btn-primary mdi mdi-pen-plus green " ng-click="grid.appScope.edit(row.entity)" >'
            },
            {
                name: 'delete',
                displayName: 'Delete',
                cellTemplate: '<button id="deleteBtn" type="button" class="btn btn-sm btn-danger mdi mdi-pen-remove red " ng-click="grid.appScope.remove(row.entity)" ></button>'
            }
        ]
    };


    $scope.allUsersDataUIGrid.onRegisterApi = function (gridApi) {
        $scope.allUsersDataUIGrid = gridApi;
        //set gridApi on scope
        $scope.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope,function(row){
            var msg = 'row selected ' + row.isSelected;

        });

        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
            var msg = 'rows changed ' + rows.length;

        });
    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.user = entity;
        $scope.roles.value=$scope.user.role;
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

    $scope.editUser = function (entity) {
        UserService.editUser(entity).then(function (result) {
            if (result.status== "201") { // check if we get the data back
                $('#userModal').modal('hide');
                $scope.editUserBol = false;
                $rootScope.runSweetAlertMsg('Edit User', 'User Edited Successfully!', 'success');
            }else{
                $rootScope.runSweetAlertMsg('Edit User', 'Edit User failed!', 'error');
            }
        });
    };

    $scope.deleteUser = function (entity) {
        UserService.deleteUser(entity).then(function (result) {
            if (result.status== "201") { // check if we get the data back

                var index = $scope.allUsersDataUIGrid.data.indexOf(entity);
                $scope.allUsersDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete User', 'User Deleted Successfully!', 'success');
            }else{
                $rootScope.runSweetAlertMsg('Delete User', 'Delete User failed!', 'error');
            }
        });

    };
}]);

