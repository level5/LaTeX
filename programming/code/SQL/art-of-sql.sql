# 如果单独有个价格表的话，查询当前价格
select a.article_name, h.price from articles a, price_history h
  where
    a.article_name = 'some_name'
  and a.article_id = h.article_id
  and h.effective_from_date =
    (
      select max(b.effective_from_date)
        from price_history b
        where a.article_id = h.article_id
    )

# 问题来了，可能有一条预先定义的明年的价格，此时最大开始时间就不是当前的价格了。

select a.article_name, h.price from articles a, price_history h
  where
    a.article_name = 'some_name'
  and a.article_id = h.article_id
  and h.effective_from_date =
    (
      select max(b.effective_from_date)
        from price_history b
        where a.article_id = h.article_id
        and b.effective_from_date < sysdate
    )

# 解决方案


# SQL中插入注释，有助于定位SQL，便于监控

# 尽量减少连接和交互的次数。

# 分析函数
