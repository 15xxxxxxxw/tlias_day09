09笔记
主要学习内容：
  1.新增员工和批量保存员工工作经历功能
  2.事务管理
  3.阿里云同步上传图片

笔记内容：
  1.数据库中一（emp)对多(emp_expr)表的主键和外键问题，<img width="901" height="669" alt="image" src="https://github.com/user-attachments/assets/bbc7d9e3-8731-4df8-908f-0f76309e409e" />
  2.事务要保存原子性，一致性。在事务的方法前加上@Transactional即可。
      事务异常：rollback回滚，propagation传播    
      @Transactional(propagation = Propagation.REQUIRES_NEW)  
      Propagation.REQUIRES_NEW:不论是否有事务,都创建新事务,运行在一个独立的事务中。
  3.在阿里云创建好access，代码中配置好阿里云的工具，application.xml和uploadController。
