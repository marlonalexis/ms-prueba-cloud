CREATE SCHEMA `pruebacloud` ;

use pruebaCloud;

CREATE USER 'pruebaCloud'@'%';
ALTER USER 'pruebaCloud'@'%'
IDENTIFIED BY '67a960d250' ;
GRANT Usage ON *.* TO 'pruebaCloud'@'%';
GRANT Alter ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Create ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Create view ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Delete ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Drop ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Grant option ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Index ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Insert ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT References ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Select ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Show view ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Trigger ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Update ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Alter routine ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Create routine ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Create temporary tables ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Execute ON pruebaCloud.* TO 'pruebaCloud'@'%';
GRANT Lock tables ON pruebaCloud.* TO 'pruebaCloud'@'%';

GRANT Select ON mysql.proc TO 'pruebaCloud'@'%';