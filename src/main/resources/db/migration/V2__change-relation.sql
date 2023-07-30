/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  rauls
 * Created: 30-jul-2023
 */

ALTER TABLE `notes` 
DROP FOREIGN KEY `notes_ibfk_1`;
ALTER TABLE `notes` 
ADD CONSTRAINT `notes_ibfk_1`
  FOREIGN KEY (`categories_id`)
  REFERENCES `categories` (`id`)
  ON DELETE SET NULL
  ON UPDATE RESTRICT;
