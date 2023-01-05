/*
 Create service level user MySQL user account.
 This is like the 'jvm' account that FBL used for the FastBridge application.
 */

 /*
  user only exists on localhost
  */

CREATE USER 'springframework'@'localhost' IDENTIFIED BY 'guru';

/*
 user only have permissions for springguru database

 */

GRANT SELECT ON springguru.* to 'springframework'@'localhost';
GRANT INSERT ON springguru.* to 'springframework'@'localhost';
GRANT DELETE ON springguru.* to 'springframework'@'localhost';
GRANT UPDATE ON springguru.* to 'springframework'@'localhost';