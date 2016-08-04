package site.laube.modelInterface;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import site.laube.dao.CompanyDao;
import site.laube.daointerface.CompanyDaoInterface;
import site.laube.database.DbConnectManager;
import site.laube.dto.temporary.CompanyDto;

public class Main {

    public static void main(String[] args) {

        // トランザクションの取得
        LocalTransaction tx = DbConnectManager.getLocalTransaction();

        try {
             // トランザクションの開始
             tx.begin();

             // aptで生成されたDaoの実装クラスを生成
             CompanyDaoInterface model = new CompanyDao();

             // 主キーでエンティティを検索
             CompanyDto companyDto = model.selectById(1);

             companyDto.setCompanyCode("NGF");
             companyDto.setCompanyName("NGF co");

             // エンティティを更新
             model.update(companyDto);

             // トランザクションのコミット
             tx.commit();
        } finally {
            // トランザクションのロールバック
            tx.rollback();
        }
    }
}