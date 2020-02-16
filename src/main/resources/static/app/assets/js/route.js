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
	}).state('stocks', {
		parent : 'nav',
		url : '/stocks',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/stocks.html',
				controller : 'ProductController'
			}
		}
	}).state('saletobranch', {
		parent : 'nav',
		url : '/saletobranch',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/saletobranch.html',
				controller : 'ProductController'
			}
		}
	}).state('saletocustomer', {
		parent : 'nav',
		url : '/saletocustomer',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/saletocustomer.html',
				controller : 'ProductController'
			}
		}
	}).state('branches', {
		parent : 'nav',
		url : '/branches',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/branches.html',
				controller : 'UsersController'
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
    }).state('icon', {
		parent : 'nav',
		url : '/icon',
		views : {
			'content@' : {
				templateUrl : 'app/views/pages/icons/material-icons.html',
				controller : 'EditProductController'
			}
		}
	}).state('glReport', {
		parent : 'nav',
		url : '/glReport',
		views : {
			'content@' : {
				templateUrl : 'app/views/glReport.html',
				controller : 'ProductController'
			}
		}
	}).state('purchaseReport', {
		parent : 'nav',
		url : '/purchaseReport',
		views : {
			'content@' : {
				templateUrl : 'app/views/purchaseReport.html',
				controller : 'ProductController'
			}
		}
	}).state('branchSaleReport', {
		parent : 'nav',
		url : '/branchSaleReport',
		views : {
			'content@' : {
				templateUrl : 'app/views/branchSaleReport.html',
				controller : 'ProductController'
			}
		}
	}).state('productCategory', {
		parent : 'nav',
		url : '/productCategory',
		views : {
			'content@' : {
				templateUrl : 'app/views/productcategory.html',
				controller : 'ProductController'
			}
		}
	}).state('productCompany', {
		parent : 'nav',
		url : '/productCompany',
		views : {
			'content@' : {
				templateUrl : 'app/views/productcompany.html',
				controller : 'ProductController'
			}
		}
	});
});
