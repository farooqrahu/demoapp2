angular.module('myApp').config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/page-not-found');
	$stateProvider.state('nav', {
		abstract : true,
		url : '',
		views : {
			'nav@' : {
				templateUrl : 'app/views/nav.html',
				controller : 'NavController'
			}
		}
	}).state('sidebar', {
		abstract : true,
		url : '',
		views : {
			'sidebar@' : {
				templateUrl : 'app/views/sidebar.html',
				controller : 'NavController'
			}
		}
	}).state('login', {

		url : '/login',
		views : {
			'content@' : {
				templateUrl : 'app/views/login.html',
				controller : 'LoginController'
			}
		}
	}).state('users', {
		parent : 'nav',
		url : '/users',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/users.html',
				controller : 'UsersController'
			}
		}
	}).state('products', {
		parent : 'nav',
		url : '/products',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/products.html',
				controller : 'ProductController'
			}
		}
	}).state('home', {
		parent : 'nav',
		url : '/home',
		views : {
			'content@' : {
				templateUrl : 'app/views/home.html',
				controller : 'HomeController'
			}
		}
	}).state('page-not-found', {
		parent : 'nav',
		url : '/page-not-found',
		views : {
			'content@' : {
				templateUrl : 'app/views/page-not-found.html',
				controller : 'PageNotFoundController'
			}
		}
	}).state('register', {
		parent : 'nav',
		url : '/register',
		views : {
			'content@' : {
				templateUrl : 'app/views/register.html',
				controller : 'RegisterController'
			}
		}
	}).state('details', {
        parent : 'nav',
        url : '/details',
        views : {
            'content@' : {
                templateUrl : 'app/views/product-details.html',
                controller : 'ProductController'
            }
        }
    }).state('new-product', {
        parent : 'nav',
        url : '/new_products',
        views : {
            'content@' : {
                templateUrl : 'app/views/product-add.html',
                controller : 'AddProductController'
            }
        }
    }).state('edit', {
        parent : 'nav',
        url : '/edit',
        views : {
            'content@' : {
                templateUrl : 'app/views/product-edit.html',
                controller : 'EditProductController'
            }
        }
    }).state('productList', {
        parent : 'nav',
        url : '/productList',
        views : {
            'content@' : {
                templateUrl : 'app/views/user-productList.html',
                controller : 'ProductlistController'
            }
        }
    });
});
