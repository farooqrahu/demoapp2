'use strict';

angular.module('myApp').controller('UsersController', ['$window', '$timeout', '$scope', '$rootScope', 'UserService', 'AuthService', function ($window, $timeout, $scope, $rootScope, UserService, AuthService) {
    var edit = false;
    this.userControllerVm = null;
    $scope.allUsers = null;
    $scope.user = {roles: [],branches:[]};
    $scope.error = "";
    $scope.editUserBol = false;
    $scope.roles = {
        "type": "select",
        "name": "role",
        "value": "Please Select",
        "values": ["Please Select"]
    };

    $scope.branches = {
        "type": "select",
        "name": "role",
        "value": "Please Select",
        "values": ["Please Select"]
    };

    $scope.findAllUsers = function () {
        UserService.findAllUsers().then(function (result) {
            if (result.status == "200") { // check if we get the data back
                $scope.allUsersDataUIGrid.data = result.data;
                UserService.findAllRoles().then(function (result) {
                    if (result.status == "200") { // check if we get the data back
                        angular.forEach(result.data, function (value, key) {
                            $scope.roles.values.push(value.name);
                        });
                        UserService.findAllBranches().then(function (result) {
                            if (result.status == "200") { // check if we get the data back
                                angular.forEach(result.data, function (value, key) {
                                    $scope.branches.values.push(value.branchName);
                                });
                            }
                        });
                    }
                });
            }
        });
    };

    $scope.setStatus = function (status) {
        $scope.user.isActive = status;
    };
    $scope.registerUser = function () {
        if ($scope.roles.value == 'Please Select' || $scope.branches.value == 'Please Select') {
            $scope.error = "Please Select";
        } else {
            $scope.user.role = $scope.roles.value;
            $scope.user.branch = $scope.branches.value;
            if ($scope.editUserBol) {
                $scope.editUser($scope.user);
            } else {
                UserService.registerUser($scope.user).then(function (result) {
                    if (result.status == "201") { // check if we get the data back
                        $scope.confirmPassword = null;
                        $scope.registerUserForm.$setPristine();
                        $('#userModal').modal('hide');
                        $scope.allUsersDataUIGrid.data.push(result.data);
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
        console.log($scope.allUsersDataUIGrid);
        $rootScope.runSweetAlertMsg('Delete All', 'Coming Soon!', 'info');
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
            {name: 'roles[0].name', displayName: "Role"},
            {
                name: 'isActive,', displayName: 'Status',
                cellTemplate: '<div ng-if="row.entity.isActive == 1"><div class="badge badge-success">Active</div></div><div ng-if="row.entity.isActive== 0"><div class="badge badge-danger">InActive</div></div>'
            },
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
       /* gridApi.selection.on.rowSelectionChanged($scope, function (row) {
            var msg = 'row selected ' + row.isSelected;

        });

        gridApi.selection.on.rowSelectionChangedBatch($scope, function (rows) {
            var msg = 'rows changed ' + rows.length;

        });*/
    };

    $scope.edit = function (entity) {
        console.log(entity);
        $scope.user = entity;
        $scope.roles.value = $scope.user.roles[0].name;
        $scope.branches.value = $scope.user.branches[0].branchName;

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

    $scope.editUser = function (entity) {
        UserService.editUser(entity).then(function (result) {
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
        UserService.deleteUser(entity).then(function (result) {
            if (result.status == "201") { // check if we get the data back

                var index = $scope.allUsersDataUIGrid.data.indexOf(entity);
                $scope.allUsersDataUIGrid.data.splice(index, 1);
                $rootScope.runSweetAlertMsg('Delete User', 'User Deleted Successfully!', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Delete User', 'Delete User failed!', 'error');
            }
        });

    };
}]);

