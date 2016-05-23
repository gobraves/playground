#!/usr/bin/python3

import argparse
import time
import os
import string

# 获取参数
parser = argparse.ArgumentParser(description="generate markdown file")
parser.add_argument('-f',dest='filename', metavar ='filename', action='store', required=True)
parser.add_argument('-c',dest='categories',metavar = 'category', action='append')
parser.add_argument('-t',dest='tags',metavar='tag', action='append')
args = parser.parse_args()
filename = args.filename
categories = args.categories
tags = args.tags

# 生成模板
taglist = ""
if tags:
    for x in tags:
        tag = " - " + x + os.linesep
        taglist = taglist + tag

new_categories = ""
if categories:
    for x in categories:
        category = " - " + x + os.linesep
        new_categories = new_categories + category

line = "---" + os.linesep
content = """\
layout: post  
title: {1} 
date: {2} 
tags: {0}{3}
categories: {0}{4} {0}""".format(os.linesep,filename,time.strftime("%Y/%m/%d",time.localtime()),taglist,new_categories)
res = line + content + line          

# 生成文件
if os.path.isfile("{0}.md".format(filename)):
    raise SystemExit('The file has existed')
else:
    mkd_file = open(filename+".md",'w')
mkd_file.write(res)

print("happy writting!!!")
