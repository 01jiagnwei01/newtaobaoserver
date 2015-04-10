ALTER TABLE `task_order`
DROP COLUMN `basic_receiver_gain_money`,
DROP COLUMN `basic_receiver_gain_point`,
DROP COLUMN `basic_pingtai_gain_point`,
DROP COLUMN `zengzhi_receiver_gain_points`,
DROP COLUMN `zengzhi_receiver_gain_money`,
DROP COLUMN `zengzhi_pingtai_gain_points`,
DROP COLUMN `repeat_plarform_grain_point`,
ADD COLUMN `pay_pingtai_points`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_for_order`,
ADD COLUMN `pay_pingtai_money`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_pingtai_points`,
ADD COLUMN `every_task_pay_pingtai_points`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_pingtai_money`,
ADD COLUMN `every_task_pay_pingtai_money`  double(10,2) NOT NULL AFTER `every_task_pay_pingtai_points`,
ADD COLUMN `every_task_pay_receiver_points`  double(10,2) NOT NULL AFTER `every_task_pay_pingtai_money`,
ADD COLUMN `every_task_pay_receiver_money`  double(10,2) NOT NULL AFTER `every_task_pay_receiver_points`;

ALTER TABLE `task_order`
DROP COLUMN `pay_for_order`;

ALTER TABLE `task_order`
CHANGE COLUMN `encourage` `commission`  double(11,2) NOT NULL DEFAULT 0.00 AFTER `guarantee_price`;

ALTER TABLE `task_order`
ADD COLUMN `basicPingtaiGainPoint`  double(10,2) NOT NULL DEFAULT 0 AFTER `every_task_pay_receiver_money`;


ALTER TABLE `task_basic`
DROP COLUMN `basic_receiver_gain_money`,
DROP COLUMN `basic_pingtai_gain_point`,
DROP COLUMN `basic_receiver_gain_point`,
DROP COLUMN `zengzhi_receiver_gain_points`,
DROP COLUMN `zengzhi_receiver_gain_money`,
DROP COLUMN `zengzhi_pingtai_gain_points`,
ADD COLUMN `pay_pingtai_points`  double(10,2) NOT NULL DEFAULT 0 AFTER `task_end_time`,
ADD COLUMN `pay_pingtai_money`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_pingtai_points`,
ADD COLUMN `pay_receiver_points`  double(10,2) NOT NULL AFTER `pay_pingtai_money`,
ADD COLUMN `pay_receiver_money`  double(10,2) NOT NULL DEFAULT 0 AFTER `pay_receiver_points`;


ALTER TABLE `task_basic`
CHANGE COLUMN `encourage` `commission`  double(11,2) NOT NULL AFTER `guarantee_price`;