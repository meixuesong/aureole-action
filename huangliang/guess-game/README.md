#####Change Log

### [2016-7-23] V1.0 release
1. 完整功能基于tdd实现,Core与Shell使用tdd开发, Main方法未写测试
2. 设计图与方法输入输出信息已提交
3. 测试数据与工时评估执行信息由于写在多页纸上，未提交。部分计划的测试数据未用到。工时评估整体上是准确的。
   编码阶段总耗时4小时左右
## 重构与优化未完成Task
1. Session Config 不使用 Ｍap, 在main中注入
2. Core package中类的拆分
3. 在图上体现类名
4. mock 的使用
5. 加入规则，只有main和factory中可以new 对象
6. Config层可调用static方法