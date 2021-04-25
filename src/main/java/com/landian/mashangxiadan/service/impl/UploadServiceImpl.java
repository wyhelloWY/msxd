package com.landian.mashangxiadan.service.impl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.landian.mashangxiadan.mapper.UserMapper;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.UploadService;
import com.landian.mashangxiadan.service.UserService;
import com.landian.mashangxiadan.utils.GetDateUtil;
import com.landian.mashangxiadan.utils.ReadExcelTest;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/10/13 16:40
 */
@Service
public class UploadServiceImpl  implements UploadService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean getExcel(MultipartFile file) throws Exception {
        // TODO Auto-generated method stub
        List<Excel> list = new ArrayList<Excel>();
        //1.得到上传的表
        Workbook workbook2 = WorkbookFactory.create(file.getInputStream());
        //2、获取test工作表
        Sheet sheet2 = workbook2.getSheetAt(0);
        //获取表的总行数
        int num = sheet2.getLastRowNum();
        System.out.println(num);
        //总列数
        int col = sheet2.getRow(0).getLastCellNum();
        System.out.println("真实行数"+ ReadExcelTest.getExcelRealRow(sheet2));
        int realNum = ReadExcelTest.getExcelRealRow(sheet2);
        boolean result = true;
        for (int j = 1; j <= realNum; j++) {
            Row row1 = sheet2.getRow(j);
            //如果单元格中有数字或者其他格式的数据，则调用setCellType()转换为string类型
            Cell cell1 = row1.getCell(0);
            cell1.setCellType(CellType.STRING);
            //获取表中第i行，第2列的单元格
            Cell cell2 = row1.getCell(1);
            cell2.setCellType(CellType.STRING);
            //excel表的第i行，第3列的单元格
            Cell cell3 = row1.getCell(2);
            cell3.setCellType(CellType.STRING);
            Cell cell4 = row1.getCell(3);
            cell4.setCellType(CellType.NUMERIC);
            UserInfo userCount = new UserInfo();
            int user_id =  Integer.valueOf(cell1.getStringCellValue());
            System.out.println(user_id);
            String user_name = cell2.getStringCellValue();
            String user_pass = cell3.getStringCellValue();
            int user_sex = (int) cell4.getNumericCellValue();
            int flag = userMapper.insertUserAll(user_id,user_name,user_pass,user_sex,
                    "软件学院",0,1);
            if(flag <=0){
                result = false;
                break;
            }
        }
        return result;
    }
}
