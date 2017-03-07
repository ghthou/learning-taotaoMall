import org.junit.Test;

import com.taotao.common.util.TaotaoResult;
import com.taotao.portal.pojo.TbItemInfo;

/**
 * @author 赵尔强
 * @Description
 * @date 2017/3/7 23:58.
 */
public class demo {
	@Test
	public void test() {
		String json = "{\"status\":200,\"msg\":\"OK\",\"data\":{\"id\":143762175492355,\"title\":\"雅培(Abbott) 亲体 金装喜康力幼儿配方奶粉 3段（1-3岁幼儿适用）900克\",\"sellPoint\":\"雅培新配方三大亲体科技，国际大奖智锁罐更安心！\",\"price\":1510,\"num\":100,\"barcode\":\"12123234234\",\"image\":\"http://192.168.1.105:8090/taotao/2017/02/26/1488114883856611,http://192.168.1.105:8090/taotao/2017/02/26/1488114883898020\",\"cid\":298,\"status\":1,\"created\":1437621754000,\"updated\":1437621754000,\"images\":[\"http://192.168.1.105:8090/taotao/2017/02/26/1488114883856611\",\"http://192.168.1.105:8090/taotao/2017/02/26/1488114883898020\"]}}";
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemInfo.class);
		System.out.println(taotaoResult);
	}
}
