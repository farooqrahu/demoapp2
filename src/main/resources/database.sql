	INSERT INTO `city` (`id`, `version`, `city_code`, `city_name`) VALUES
	(1, 0, 'M', 'Moro');

INSERT INTO `branch` (`id`, `version`, `address`, `branch_code`, `branch_name`, `phone`, `city_id`) VALUES
	(1, 0, 'Bandhi Road', '1', 'Nadeem', NULL, 1),
	(2, 0, 'Dars Road', '2', 'Tanveer', NULL, 1),
	(3, 0, 'Khera Bazar', '3', 'Jamil Admin', NULL, 1),
	(4, 0, 'Bazar', '4', 'Jabar', NULL, 1);

	INSERT INTO `account` (`id`, `version`, `created_at`, `created_by`, `updated_at`, `updated_by`, `branch_id`, `code`, `is_active`, `posting_end_date`, `posting_start_date`, `title`) VALUES
	(1, 0, NULL, NULL, NULL, NULL, 3, 'PA', b'1', NULL, NULL, 'PURCHASES_ACCOUNT'),
	(2, 0, NULL, NULL, NULL, NULL, 3, 'VA', b'1', NULL, NULL, 'VENDOR_ACCOUNT'),
	(3, 0, NULL, NULL, NULL, NULL, 3, 'CSA', b'1', NULL, NULL, 'CUSTOMER_SALE_ACCOUNT'),
	(4, 0, NULL, NULL, NULL, NULL, 3, 'IA', b'1', NULL, NULL, 'INCOME_ACCOUNT'),
	(5, 0, NULL, NULL, NULL, NULL, 3, 'BSA', b'1', NULL, NULL, 'BRANCH_SALE_ACCOUNT'),
	(6, 0, NULL, NULL, NULL, NULL, 2, 'TBA', b'1', NULL, NULL, 'TANVEER_BRANCH_ACCOUNT'),
	(7, 0, NULL, NULL, NULL, NULL, 1, 'NBA', b'1', NULL, NULL, 'NADEEM_BRANCH_ACCOUNT'),
	(8, 0, NULL, NULL, NULL, NULL, 4, 'JBA', b'1', NULL, NULL, 'JABAR_BRANCH_ACCOUNT');



	INSERT INTO `customer_type` (`id`, `version`, `customer_type`, `description`) VALUES
	(1, 0, 'COUNTER_CUSTOMER', NULL),
	(2, 0, 'BRANCH_CUSTOMER', NULL);


	--------------------

	INSERT INTO `role` (`id`, `version`, `created_at`, `created_by`, `updated_at`, `updated_by`, `name`) VALUES
	(1, NULL, NULL, NULL, NULL, NULL, 'ADMIN'),
	(2, NULL, NULL, NULL, NULL, NULL, 'OFFICER'),
	(3, NULL, NULL, NULL, NULL, NULL, 'MANAGER'),
	(4, NULL, NULL, NULL, NULL, NULL, 'DEO');

	INSERT INTO `user` (`id`, `version`, `created_at`, `created_by`, `updated_at`, `updated_by`, `closed`, `designation`, `email`, `employee_id`, `is_active`, `name`, `password`, `phone_no`, `username`) VALUES
	(1, 1, '2020-01-13 20:53:18', NULL, '2020-07-16 21:32:05', NULL, b'0', 'Admin', 'jamilkamboh@gmail.com', '3', b'1', 'Jamil', '$2a$10$l7vnugmi4LOP8rntsGpmHeFFBGiOONMrIPVZsYjdO611ASsND/KFO', '234234234', 'jamil');



INSERT INTO `user_branches` (`user_id`, `branches_id`) VALUES
	(1, 1);


	INSERT INTO `user_roles` (`user_id`, `roles_id`) VALUES
	(1, 1);

