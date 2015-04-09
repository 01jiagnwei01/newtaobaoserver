ALTER TABLE `task_order`
DROP COLUMN `basic_receiver_gain_money`,
DROP COLUMN `basic_receiver_gain_point`,
DROP COLUMN `basic_pingtai_gain_point`,
DROP COLUMN `zengzhi_receiver_gain_points`,
DROP COLUMN `zengzhi_receiver_gain_money`,
DROP COLUMN `zengzhi_pingtai_gain_points`,
ADD COLUMN `pay_pingtai_points`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_for_order`,
ADD COLUMN `pay_pingtai_money`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_pingtai_points`,
ADD COLUMN `every_task_pay_pingtai_points`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_pingtai_money`,
ADD COLUMN `every_task_pay_pingtai_money`  double(10,2) NOT NULL AFTER `every_task_pay_pingtai_points`,
ADD COLUMN `every_task_pay_receiver_points`  double(10,2) NOT NULL AFTER `every_task_pay_pingtai_money`,
ADD COLUMN `every_task_pay_receiver_money`  double(10,2) NOT NULL AFTER `every_task_pay_receiver_points`;