package com.dy.vam.entity;


import java.util.List;

/**
 * Created by James on 2018/3/28.
 */

public class Notice {


    /**
     * code : 0
     * msg :
     * data : {"page_now":1,"has_next":true,"list":{"total":18,"per_page":10,"current_page":1,"last_page":2,"data":[{"type":3,"title":"报销审批","content":{"msg_content":3,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：100元"},"msg":"你的报销申请已同意，请知晓","status":"已通过","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：1元"},"msg":"用户1提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1144,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：8元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1144.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1147,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：5元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"},{"type":1,"title":"报销审批","content":{"msg_content":1,"expenseid":"1147","line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：500元"},"msg":"黄金的报销需要您审批","status":"待我审核","color":"#F5A623","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"}]}}
     */

    private int code;
    private String msg;
    private DataBeanX data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * page_now : 1
         * has_next : true
         * list : {"total":18,"per_page":10,"current_page":1,"last_page":2,"data":[{"type":3,"title":"报销审批","content":{"msg_content":3,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：100元"},"msg":"你的报销申请已同意，请知晓","status":"已通过","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：1元"},"msg":"用户1提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1144,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：8元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1144.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1147,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：5元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"},{"type":1,"title":"报销审批","content":{"msg_content":1,"expenseid":"1147","line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：500元"},"msg":"黄金的报销需要您审批","status":"待我审核","color":"#F5A623","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"}]}
         */

        private int page_now;
        private boolean has_next;
        private ListBean list;

        public int getPage_now() {
            return page_now;
        }

        public void setPage_now(int page_now) {
            this.page_now = page_now;
        }

        public boolean isHas_next() {
            return has_next;
        }

        public void setHas_next(boolean has_next) {
            this.has_next = has_next;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 18
             * per_page : 10
             * current_page : 1
             * last_page : 2
             * data : [{"type":3,"title":"报销审批","content":{"msg_content":3,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：100元"},"msg":"你的报销申请已同意，请知晓","status":"已通过","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：1元"},"msg":"用户1提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1144,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：8元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1144.html"},{"type":2,"title":"抄送","content":{"msg_content":2,"expenseid":1147,"line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：5元"},"msg":"黄金提交的报销申请已通过，抄送给你，请知晓","status":"已抄送","color":"#2CDA9C","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"},{"type":1,"title":"报销审批","content":{"msg_content":1,"expenseid":"1147","line1":"报销人：黄金","line2":"报销类型：办公费","line3":"报销金额：500元"},"msg":"黄金的报销需要您审批","status":"待我审核","color":"#F5A623","createtime":null,"username":"用户1","headimg":"http://yj.tigerisa.com/uploads/user/11526369744.png","url":"http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1147.html"}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * type : 3
                 * title : 报销审批
                 * content : {"msg_content":3,"expenseid":1148,"line1":"报销人：用户1","line2":"报销类型：办公费","line3":"报销金额：100元"}
                 * msg : 你的报销申请已同意，请知晓
                 * status : 已通过
                 * color : #2CDA9C
                 * createtime : null
                 * username : 用户1
                 * headimg : http://yj.tigerisa.com/uploads/user/11526369744.png
                 * url : http://yj.tigerisa.com/main/expense/examine/token/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6MiwiY29tcGFueWlkIjo4LCJleHAiOjE1ODU2NDQ0MzJ9.-o8H2WPm-tpmXusdm2bdS-PCAh_W3kVTBqiSsLTKHsw/expenseid/1148.html
                 */

                private int type;
                private String title;
                private ContentBean content;
                private String msg;
                private String status;
                private String color;
                private String createtime;
                private String username;
                private String headimg;
                private String url;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public ContentBean getContent() {
                    return content;
                }

                public void setContent(ContentBean content) {
                    this.content = content;
                }

                public String getMsg() {
                    return msg;
                }

                public void setMsg(String msg) {
                    this.msg = msg;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getHeadimg() {
                    return headimg;
                }

                public void setHeadimg(String headimg) {
                    this.headimg = headimg;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public static class ContentBean {
                    /**
                     * msg_content : 3
                     * expenseid : 1148
                     * line1 : 报销人：用户1
                     * line2 : 报销类型：办公费
                     * line3 : 报销金额：100元
                     */

                    private int msg_content;
                    private int expenseid;
                    private String line1;
                    private String line2;
                    private String line3;

                    public int getMsg_content() {
                        return msg_content;
                    }

                    public void setMsg_content(int msg_content) {
                        this.msg_content = msg_content;
                    }

                    public int getExpenseid() {
                        return expenseid;
                    }

                    public void setExpenseid(int expenseid) {
                        this.expenseid = expenseid;
                    }

                    public String getLine1() {
                        return line1;
                    }

                    public void setLine1(String line1) {
                        this.line1 = line1;
                    }

                    public String getLine2() {
                        return line2;
                    }

                    public void setLine2(String line2) {
                        this.line2 = line2;
                    }

                    public String getLine3() {
                        return line3;
                    }

                    public void setLine3(String line3) {
                        this.line3 = line3;
                    }
                }
            }
        }
    }
}
