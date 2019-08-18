package com.akexorcist.complexrecyclerviewr.api

import com.akexorcist.complexrecyclerviewr.vo.ArticleCategoryResult
import com.akexorcist.complexrecyclerviewr.vo.ArticleGroupResult
import com.akexorcist.complexrecyclerviewr.vo.Profile

object MockData {
    fun getAkexorcistProfile() = Profile(
        name = "Akexorcist",
        job = "Android Developer"
    )

    fun getArticleCategories() = arrayListOf(
        ArticleCategoryResult.Category("0872811069", "Sleeping For Less"),
        ArticleCategoryResult.Category("9785588260", "Medium")
    )

    fun getSleepingForLessArticleGroup() = arrayListOf(
        ArticleGroupResult.Group(
            id = "3425171491",
            name = "Code",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "8639741400",
                    "รู้จักกับ ViewPager2 ที่จะมาแทน ViewPager แบบเดิมๆ"
                ),
                ArticleGroupResult.Topic(
                    "4548078611",
                    "จะใช้ ViewModel หรือ AndroidViewModel ดี?"
                ),
                ArticleGroupResult.Topic(
                    "3181072740",
                    "มาเปลี่ยน Dependency Injection ให้เป็นเรื่องง่ายด้วย Koin กันดูมั้ย?"
                ),
                ArticleGroupResult.Topic(
                    "9695565584",
                    "เปลี่ยน Launch Screen ให้ดูดีกว่าที่เคยเป็นด้วยเทคนิคง่ายๆกันเถอะ"
                )
            )
        ),
        ArticleGroupResult.Group(
            id = "6348661137",
            name = "Design",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "3927470097",
                    "รับมือกับปัญหาชวนปวดหัวกับขนาดของตัวอักษรเมื่อต้องเจอกับฟอนต์ที่หลากหลาย"
                ),
                ArticleGroupResult.Topic(
                    "3199270238",
                    "มารู้จักกับ Prototyping Tools สำหรับงาน Mobile Application กันเถอะ"
                ),
                ArticleGroupResult.Topic(
                    "9601937954",
                    "Style and Theme - เพราะชีวิตต้องมีสไตล์"
                )
            )
        ),
        ArticleGroupResult.Group(
            id = "3307264984",
            name = "Dev Tips",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "1356681305",
                    "เปิดโปรเจค Android Studio ผ่าน Command Line บน Mac OS และ Linux"
                ),
                ArticleGroupResult.Topic(
                    "5306750685",
                    "รู้จักกับ Biometric Authentication บนแอนดรอยด์"
                ),
                ArticleGroupResult.Topic(
                    "3432581276",
                    "ว่าด้วยเรื่อง Issue ของ Activity Stack สุดแปลกที่ไม่เคยเจอมาก่อน"
                ),
                ArticleGroupResult.Topic(
                    "1489124549",
                    "เมื่อ Google Play บังคับให้นักพัฒนาต้องใส่ Privacy Policy เมื่อเข้าถึงความเป็นส่วนตัวของผู้ใช้"
                ),
                ArticleGroupResult.Topic(
                    "7847880485",
                    "วิธีการสร้าง Keystore บน Android Studio"
                )
            )
        )
    )

    fun getMediumArticleGroup() = arrayListOf(
        ArticleGroupResult.Group(
            id = "9872061229",
            name = "Work Life",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "1439395697",
                    "อย่าทำงานของตัวเองให้สมบูรณ์แบบจนเกินไป"
                ),
                ArticleGroupResult.Topic(
                    "0564368983",
                    "ถ้าจะทำ Mobile App ควรทำเป็น Native หรือ Hybrid ดี?"
                ),
                ArticleGroupResult.Topic(
                    "7042471770",
                    "อย่าให้สวัสดิการในบริษัทซอฟต์แวร์ยุคใหม่ทำให้เราเสียนิสัย"
                ),
                ArticleGroupResult.Topic(
                    "7884584724",
                    "เงินเดือนในบัญชีหมดได้ แต่อย่าให้ไฟในการทำงานนั้นหมดไป"
                ),
                ArticleGroupResult.Topic(
                    "9376959895",
                    "ทำไมการเลือกรับ Developer เข้าทีมถึงควรแปรผันตามกาลเวลาและการเติบโตของบริษัท"
                )
            )
        ),
        ArticleGroupResult.Group(
            id = "5899344011",
            name = "Life Style",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "4988025033",
                    "Presentation Slide ที่ดีไม่ควรมองข้ามเรื่อง “แสง”"
                ),
                ArticleGroupResult.Topic(
                    "4098908374",
                    "มื้อเย็น 7 วันของผมขึ้นอยู่กับน้องในทีมด้วยงบ 50 บาท"
                ),
                ArticleGroupResult.Topic(
                    "1061011712",
                    "ยกแก๊งค์ไปพักผ่อนหย่อนใจ @ Lotus Villas & Resort ณ หัวหิน"
                )
            )
        ),
        ArticleGroupResult.Group(
            id = "2362868883",
            name = "Kotlin",
            topicList = arrayListOf(
                ArticleGroupResult.Topic(
                    "0349345583",
                    "ทำไมถึงต้องใช้ Data Class ใน Kotlin?"
                ),
                ArticleGroupResult.Topic(
                    "9100475773",
                    "มาเปลี่ยน Push Notification บน Android จาก GCM ไปเป็น FCM กัน"
                ),
                ArticleGroupResult.Topic(
                    "2689615318",
                    "สร้าง Curve Seek Bar ใช้เองบน React"
                )
            )
        )
    )
}