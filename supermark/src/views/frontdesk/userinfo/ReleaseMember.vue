<template>
    <div>

        <div style="margin-top: 2rem;margin-bottom: 2rem;">
            <a style="margin-left: 30px;" href="javascript:history.back (-1)" class="el-dropdown-link">
                <i class="el-icon-arrow-left"></i>返回
            </a>
        </div>
        <div v-loading="listLoading" class="infinite-list" v-infinite-scroll="load" style="overflow:auto;height: 37rem;">
            <div>

                <div style="margin-left: 4rem;margin-right: 2rem;height: 18rem;">
                    <div style="height: 15rem;width: 100%;">
                        <img :src="this.goodsForm.defaultImg" style="width: 15rem;height: 15rem;float: left;margin: 15px;">
                        <div style="float: left;margin-top: 1rem;margin-left: 1rem;">
                            <div style="float: left;font-size: 1.5rem;">{{ this.goodsForm.name }}</div>
                            <div style="float: right;width: 10rem;font-size: 1.2rem;">价格：<span
                                    style="color:rgb(234, 12, 12);font-size: 1.4rem;">¥ {{ this.goodsForm.price }}</span>
                            </div>
                            <div style="height: 1rem;"></div>
                            <div style="clear: left;width: 55rem;clear: left;margin-top: 3rem;height: 2.4rem;">
                                <div><span>描述：</span><span style="color: #717171;font-size: 15px;word-break: break-all;overflow: hidden;display: -webkit-box;
                                    -webkit-line-clamp: 2;-webkit-box-orient: vertical;">{{ this.goodsForm.description }}</span>
                                </div>
                            </div>
                            <!-- <span v-for="img in images" :key="img.id">
                            <el-image style="width: 180px;height: 180px;" alt="无法显示" :src="img.url"
                                :preview-src-list="image" v-if="[[images != null]]" />
                        </span> -->
                            <div style="height: 1rem;"></div>
                            <div style="clear: left;">
                                <span v-for="img in images" :key="img.id">
                                    <el-image style="width: 6rem;height: 6rem;float: left;margin: 15px;" alt="无法显示"
                                        :src="img.url" :preview-src-list="image" v-if="[[images != null]]" />
                                </span>
                            </div>
                            <div style="height: 2rem;clear: left;"></div>

                        </div>
                    </div>
                </div>
                <div>
                </div>
                <div style="float: left;margin-left: 30px;">
                    <template>
                        <el-table v-loading="listLoading" :row-style="{ height: '45px' }" :cell-style="{ padding: '0px' }"
                            :data="List" element-loading-text="Loading" border fit highlight-current-row>
                            <el-table-column width="55">
                                <template v-slot="scope">
                                    <span style="margin-left: 10px;" @click="copy(scope.row.orderAddr)"
                                        class="el-icon-document-copy"></span>
                                </template>
                            </el-table-column>
                            <el-table-column align="center" label="买家名称" prop="order.memberUsername" width="110 "
                                :show-overflow-tooltip="true">

                            </el-table-column>
                            <el-table-column align="center" label="名字" prop="orderAddr.consignee" width="110 "
                                :show-overflow-tooltip="true" />
                            <el-table-column align="center" label="省" prop="orderAddr.province" width="90"
                                :show-overflow-tooltip="true" />
                            <el-table-column align="center" label="市" prop="orderAddr.city" width="100"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="区\县" align="center" prop="orderAddr.area" width="100" />
                            <el-table-column label="详细地址" align="center" prop="orderAddr.addr" width="150"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="付款金额" align="center" prop="order.payAmount" width="150"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="购买时间" align="center" prop="order.createTime" width="140"
                                :show-overflow-tooltip="true" />
                            <el-table-column label="付款时间" align="center" prop="order.payTime" width="140"
                                :show-overflow-tooltip="true" />
                            <el-table-column align="center" prop="order" label="操作" width="130">
                                <template v-slot="scope">
                                    <el-button type="primary" icon="el-icon-edit" size="mini"
                                        @click="updateAddr(scope.row.order.id)"
                                        v-if="scope.row.order.state == 1">填写快递单号</el-button>
                                    <div v-if="scope.row.order.state == 0">未付款</div>
                                    <div v-if="scope.row.order.state == 2">已发货</div>
                                    <div v-if="scope.row.order.state == 3">已完成</div>
                                    <div v-if="scope.row.order.state == 4">已取消</div>
                                </template>
                            </el-table-column>
                        </el-table>

                        <!-- <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                            :current-page="queryInfo.pagenum" :page-sizes="[5, 10, 15]" :page-size="queryInfo.pagesize"
                            layout="total, sizes, prev, pager, next, jumper" :total="total">
                        </el-pagination> -->
                    </template>

                </div>
            </div>
        </div>
        <el-dialog :title="goodsFormTitle" :visible.sync="goodsFormShow" width="40rem">
            <el-form :model="goodsForm" label-width="80px">
                <el-form-item label="快递公司" prop="region">
                    <el-select v-model="goodsForm.deliveryCompany" placeholder="请选择快递">
                        <el-option label="邮政快递" value="邮政快递"></el-option>
                        <el-option label="顺丰快递" value="顺丰快递"></el-option>
                        <el-option label="京东快递" value="京东快递"></el-option>
                        <el-option label="中通快递" value="中通快递"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="deliverySn" label="快递单号">
                    <el-input v-model="goodsForm.deliverySn" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="closeDialog">关 闭</el-button>
                <el-button type="primary" @click="handleSave()">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</template>
 
<script>
import ClipboardJS from 'clipboard';
export default {
    mounted() {

    },
    data() {
        return {
            region: '',
            input3: '',
            status: 1,
            addr: {},
            goodsFormTitle: '填写快递单号',
            goodsFormShow: false,
            goodsForm: {},
            goodsId: 0,
            addrId: 0,
            index: 0,
            listLoading: false,
            // 定义数据源
            List: [],
            //查询分页信息
            queryInfo: {
                uid: JSON.parse(sessionStorage.getItem("user")).uid,
                pagenum: 1,
                pagesize: 5,
            },
            images: '',
            image: [],
            delivery: ['邮政', '顺丰'],
            order: ''
        }
    },
    created() {
        this.master = JSON.parse(sessionStorage.getItem("user")).username;
        if (this.$route.query.goodsId != undefined) {
            this.goodsId = this.$route.query.goodsId;
        }
        this.getList()
        // this.getOrderAddrs()
    },
    methods: {
        updateAddr(orderId) {

            this.goodsForm.orderId = orderId
            this.goodsFormShow = true;
        },
        payment(orderSn) {
            // window.location.href("/checkstand/"+orderSn)
            this.$router.push({ path: "/checkstand", query: { 'orderSn': orderSn } });
        },
        save() {
            console.log(this.list[this.index])
            if (this.addr != 0) {
                this.$http.post("/api/cloud-order/orderaddr/updates", { id: this.goodsForm.orderAddr.id, orderAddr: this.list[this.index] }).then(res => {
                    this.goodsFormShow = false;
                    this.getList()
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                });
            }
        },
        closeDialog() { this.goodsFormShow = false; this.goodsFrom = {} },
        getList() {
            this.listLoading = true
            var memberId = JSON.parse(sessionStorage.getItem("user_id"));
            console.log(this.orderId)
            this.$http.get("/api/cloud-order/orderaddr/getGoods?goodsId=" + this.goodsId
            ).then(res => {
                this.goodsForm = res.data.data.goodsDTO
                console.log(this.goodsForm.name)
                this.List = res.data.data.orderInfoVos
                this.images = res.data.data.goodsDTO.images
                console.log(this.List)
                this.images.forEach(image => {
                    this.image.push(image.url)
                });
                this.listLoading = false
            });
        },
        handleSave() {
            this.$http.post("/api/cloud-order/orderaddr/updateOrder", this.goodsForm
            ).then(res => {
                this.$message({
                    message: '填写成功',
                    type: 'success'
                });
                this.getList()
                this.goodsFormShow = false;
            });
        },
        copy(order) {
            // new ClipboardJS('.copy-btn');
            // this.$message({
            //     message: '复制成功',
            //     type: 'success'
            // });
            console.log(order)
            this.order = "姓名：" + order.consignee + "  地址：" + order.province + " " + order.city + " " + order.area +
                "  详细地址" + order.addr + " 电话号码：" + order.mobile
            //创建input标签
            var input = document.createElement('input')
            //将input的值设置为需要复制的内容
            input.value = this.order;
            //添加input标签
            document.body.appendChild(input)
            //选中input标签
            input.select()
            //执行复制
            document.execCommand('copy')
            //成功提示信息
            this.$message.success('复制成功！')
            //移除input标签
            document.body.removeChild(input)
        }
    }
}
</script>
<style></style>

