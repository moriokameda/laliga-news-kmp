package data.repository

import data.model.NewsArticle

class SampleNewsRepository : NewsRepository {
    override suspend fun getNewsList(): List<NewsArticle> {
        return listOf(
            NewsArticle(
                id = "1",
                title = "レアル・マドリード、エル・クラシコで圧勝",
                summary = "サンティアゴ・ベルナベウでの一戦でレアル・マドリードがバルセロナを3-0で下しました。",
                imageUrl = "https://images.unsplash.com/photo-1551698618-1dfe5d97d256?w=400&h=300&fit=crop",
                publishDate = "2024-03-15"
            ),
            NewsArticle(
                id = "2",
                title = "バルセロナ、新戦力ガビが大活躍",
                summary = "カンプ・ノウでの試合でガビが2ゴールを決め、チームの勝利に貢献しました。",
                imageUrl = "https://images.unsplash.com/photo-1529900748604-07564a03e7a6?w=400&h=300&fit=crop",
                publishDate = "2024-03-14"
            ),
            NewsArticle(
                id = "3",
                title = "アトレティコ・マドリード、連勝記録更新",
                summary = "ワンダ・メトロポリターノでの試合でアトレティコが5連勝を達成しました。",
                imageUrl = "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=400&h=300&fit=crop",
                publishDate = "2024-03-13"
            ),
            NewsArticle(
                id = "4",
                title = "セビージャFC、ヨーロッパリーグ進出決定",
                summary = "ラ・リーガでの健闘によりセビージャがヨーロッパリーグ出場権を獲得しました。",
                imageUrl = "https://images.unsplash.com/photo-1574629810360-7efbbe195018?w=400&h=300&fit=crop",
                publishDate = "2024-03-12"
            ),
            NewsArticle(
                id = "5",
                title = "ビルバオ、コパ・デル・レイ準決勝進出",
                summary = "アスレティック・ビルバオがコパ・デル・レイ準決勝への切符を手にしました。",
                imageUrl = "https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?w=400&h=300&fit=crop",
                publishDate = "2024-03-11"
            ),
            NewsArticle(
                id = "6",
                title = "レアル・ソシエダ、若手選手が躍進",
                summary = "20歳のMFが今シーズン5得点目を記録し、チームの躍進に貢献しています。",
                imageUrl = "https://images.unsplash.com/photo-1543326727-cf6c39e8f84c?w=400&h=300&fit=crop",
                publishDate = "2024-03-10"
            ),
            NewsArticle(
                id = "7",
                title = "バレンシアCF、ホームで劇的勝利",
                summary = "メスタージャでの一戦で90分にゴールを決め、2-1の勝利を収めました。",
                imageUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=400&h=300&fit=crop",
                publishDate = "2024-03-09"
            ),
            NewsArticle(
                id = "8",
                title = "ビジャレアル、チャンピオンズリーグ圏内浮上",
                summary = "エスタディオ・デ・ラ・セラミカでの勝利によりCL圏内の4位に浮上しました。",
                imageUrl = "https://images.unsplash.com/photo-1553778263-73a83bab9b0c?w=400&h=300&fit=crop",
                publishDate = "2024-03-08"
            ),
            NewsArticle(
                id = "9",
                title = "ラス・パルマス、昇格後初勝利",
                summary = "1部復帰後苦戦していたラス・パルマスがようやく今季初勝利を挙げました。",
                imageUrl = "https://images.unsplash.com/photo-1489944440615-453fc2b6a9a9?w=400&h=300&fit=crop",
                publishDate = "2024-03-07"
            ),
            NewsArticle(
                id = "10",
                title = "ヘタフェ、堅守で勝ち点3を獲得",
                summary = "コリセウム・アルフォンソ・ペレスでヘタフェが1-0の勝利を収めました。",
                imageUrl = "https://images.unsplash.com/photo-1544717684-b6a26df9e6b8?w=400&h=300&fit=crop",
                publishDate = "2024-03-06"
            )
        )
    }
}