# -*- coding: utf-8 -*-

import pandas as pd
from pandas import DataFrame

import sqlite3
import csv

csvF= open('d:/nodejshm/db/databases/output.csv', 'w', encoding='utf-8', newline='')
csvWr= csv.writer(csvF)

listArray= []
listDic= {}

def all_listView_subject():
    conn= sqlite3.connect("d:/nodejshm/db/databases/hm.db")
    cursor= conn.cursor()
    cursor.execute("select * from hm_subject")
    lists= cursor.fetchall()
    for list in lists:
        print(list)
    conn.close()
    
def all_listView_student():
    conn= sqlite3.connect("d:/nodejshm/db/databases/hm.db")
    cursor= conn.cursor()
    cursor.execute("select * from hm_student")
    lists= cursor.fetchall()   
    for list in lists:
        print(list)
    conn.close()  
    
def test223():
    conn= sqlite3.connect("d:/nodejshm/db/databases/hm.db")
    df= pd.read_sql_query("select stu_name from hm_rest_table", conn)
    print(df)
    
def all_listView_rest_table():
    try:
        conn= sqlite3.connect("d:/nodejshm/db/databases/hm.db")
        cursor= conn.cursor()
        cursor.execute("select * from hm_rest_table")
        lists= cursor.fetchall()
        for listx in lists:
            csvWr.writerow(listx)
        
        cursor.execute("select stu_name from hm_rest_table")
        lists= cursor.fetchall()
        for list in lists:
            print("이름: %s" %(list[0]))
            if list[0] in listArray:
                listDic[list[0]]+= 1
            else :
                listArray.append(list[0])
                listDic[list[0]]= 1
    except Exception as err :
        print('error', err)
    finally:
        csvF.close()
        conn.close()   

def excTest():
    print(listDic)
    df = DataFrame(index=["휴가횟수"])
    for test in listDic.items():
        df[test[0]]=[listDic.get(test[0])]
    print(df)

    writer = pd.ExcelWriter('d:/nodejshm/db/databases/pandasExc.xlsx', engine='xlsxwriter')
    df.to_excel(writer, sheet_name='Sheet1')
    # Pandas writer 객체에서 xlsxwriter 객체
    workbook = writer.book
    worksheet = writer.sheets['Sheet1']
      
    #포매팅, 아스키코드
    start="B"
    print(type(len(listDic)))
    
    chart = workbook.add_chart({'type':'column'})
    chart.add_series({'values':'=Sheet1!%s$2:%s$2' %(start, chr(ord(start)+len(listDic)-1))})
    worksheet.insert_chart('%s$6' %(chr(ord(start)+2)), chart)
    writer.close()
    

#all_listView_subject()
all_listView_rest_table()
#all_listView_student()

excTest()
#test223()


