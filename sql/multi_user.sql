ALTER TABLE account_balance ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE accounts ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE activa ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE boekwaarde ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE restwaarde ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE private_expenses ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE kosten ADD COLUMN `user_id` bigint(20) NOT NULL AFTER id;
ALTER TABLE kosten DROP crediteur_id;

 
