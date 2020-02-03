SET CONSTRAINTS ALL DEFERRED;

delete from category where id >= 50;
delete from product where id >= 50;

