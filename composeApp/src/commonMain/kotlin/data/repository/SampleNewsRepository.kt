package data.repository

import data.model.NewsArticle

class SampleNewsRepository : NewsRepository {
    override suspend fun getNewsList(): List<NewsArticle> {
        return listOf(
            NewsArticle(
                id = "1",
                title = "レアル・マドリード、エル・クラシコで圧勝",
                summary = "サンティアゴ・ベルナベウでの一戦でレアル・マドリードがバルセロナを3-0で下しました。",
                content = "サンティアゴ・ベルナベウで行われたエル・クラシコは、レアル・マドリードがバルセロナを3-0で完勝する結果となった。\n\n前半15分、ヴィニシウス・ジュニオールが先制ゴールを記録。その後も攻撃の手を緩めないマドリードは、35分にベンゼマが追加点を奪った。\n\n後半に入ってもマドリードの勢いは止まらず、70分にはモドリッチの見事なミドルシュートがネットを揺らし3-0とした。バルセロナも反撃を試みたが、マドリードの堅固な守備を破ることはできなかった。\n\nこの勝利により、マドリードはリーグ首位をより確固たるものとし、シーズン終盤に向けて大きな弾みをつけることとなった。アンチェロッティ監督は試合後、「チーム全体の献身的なプレーが勝利につながった」とコメントしている。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1551698618-1dfe5d97d256?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1574629810360-7efbbe195018?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-15 22:30",
                source = "ESPN",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "2",
                title = "バルセロナ、新戦力ガビが大活躍",
                summary = "カンプ・ノウでの試合でガビが2ゴールを決め、チームの勝利に貢献しました。",
                content = "カンプ・ノウで行われたラ・リーガ第28節で、バルセロナが新戦力ガビの活躍により3-1で勝利を収めた。\n\n19歳のガビは前半20分と後半55分に立て続けにゴールを決め、チームの勝利に大きく貢献した。特に2点目のゴールは、ペドリからの絶妙なパスを受けての見事なボレーシュートで、スタジアムを大いに沸かせた。\n\n監督のシャビは試合後、「ガビは我々の将来を担う選手だ。今日のプレーがそれを証明している」と若手の成長を賞賛した。\n\nこの勝利によりバルセロナはリーグ戦での連勝を3試合に伸ばし、上位争いにおいて重要な3ポイントを獲得することとなった。次節も引き続きガビの活躍に期待が集まる。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1529900748604-07564a03e7a6?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-14 21:45",
                source = "Goal.com",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "3",
                title = "Atlético Madrid extends winning streak",
                summary = "ワンダ・メトロポリターノでの試合でアトレティコが5連勝を達成しました。",
                content = "Atlético Madrid continued their impressive form with a 2-0 victory at the Wanda Metropolitano, extending their winning streak to five games.\n\nThe match saw brilliant performances from key players, with goals coming in the second half. The team's defensive solidity has been particularly noteworthy during this winning run, having conceded only two goals in their last five matches.\n\nManager Diego Simeone praised his team's consistency and determination. 'The players are showing great commitment and understanding of our tactical approach,' he said in the post-match interview.\n\nThis victory strengthens Atlético's position in the league table and demonstrates their strong form heading into the crucial final weeks of the season. The team's supporters were in full voice throughout the match, creating an electric atmosphere that clearly motivated the players.",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1489944440615-453fc2b6a9a9?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-13 20:15",
                source = "Marca",
                originalLanguage = "英語",
                isTranslated = true
            ),
            NewsArticle(
                id = "4",
                title = "セビージャFC、ヨーロッパリーグ進出決定",
                summary = "ラ・リーガでの健闘によりセビージャがヨーロッパリーグ出場権を獲得しました。",
                content = "セビージャFCが今シーズンのラ・リーガでの健闘により、来シーズンのヨーロッパリーグ出場権を獲得した。\n\n残り数試合を残して数学的に出場権を確定させたセビージャは、今シーズン安定した戦いを見せている。特に守備陣の連携が素晴らしく、失点数はリーグでも上位に位置している。\n\n監督のメンディリバルは「チーム全体の努力が実を結んだ。選手たちは素晴らしい集中力を見せてくれた」と語った。\n\nクラブにとってヨーロッパリーグは得意な舞台でもあり、ファンは来シーズンの活躍を期待している。また、この出場権獲得により、夏の移籍市場でもより良い選手を獲得できる可能性が高まった。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1574629810360-7efbbe195018?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1543326727-cf6c39e8f84c?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1553778263-73a83bab9b0c?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-12 19:30",
                source = "AS",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "5",
                title = "Athletic Bilbao reaches Copa del Rey semifinals",
                summary = "アスレティック・ビルバオがコパ・デル・レイ準決勝への切符を手にしました。",
                content = "Athletic Bilbao secured their place in the Copa del Rey semifinals with a thrilling 2-1 victory over their opponents in a match that showcased the best of Spanish football.\n\nThe Basque side showed great character throughout the match, particularly in the second half when they overturned a 1-0 deficit. The winning goal came in the 85th minute, sending the traveling fans into raptures.\n\nThis achievement is particularly significant for Athletic Bilbao, as the Copa del Rey holds special importance for the club and its supporters. The team's unique philosophy of only fielding Basque players makes every success even more meaningful.\n\nCoach Ernesto Valverde expressed his pride in the team's performance: 'The players showed the true spirit of Athletic today. This is what our club represents - passion, determination, and never giving up.'\n\nThe semifinal draw will determine their next opponents as they continue their quest for Copa del Rey glory.",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1544717684-b6a26df9e6b8?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-11 22:00",
                source = "Sky Sports",
                originalLanguage = "英語",
                isTranslated = true
            ),
            NewsArticle(
                id = "6",
                title = "Real Sociedad: joven talento en ascenso",
                summary = "20歳のMFが今シーズン5得点目を記録し、チームの躍進に貢献しています。",
                content = "La Real Sociedad continúa con su excelente temporada, y mucho de ello se debe al surgimiento de jóvenes talentos en el equipo. El mediocampista de 20 años ha sido una revelación esta temporada.\n\nCon su quinto gol de la temporada, el joven jugador ha demostrado que puede ser una pieza clave en el futuro del club. Su técnica, visión de juego y capacidad goleadora lo convierten en uno de los prospectos más prometedores del fútbol español.\n\nEl entrenador Imanol Alguacil ha elogiado constantemente el desarrollo del jugador: 'Su progreso ha sido excepcional. Tiene la mentalidad correcta y el talento necesario para llegar muy lejos.'\n\nLa afición de la Real Sociedad está emocionada con este nuevo talento, que representa la cantera del club y sus valores de desarrollar jugadores locales. Su rendimiento esta temporada ha sido crucial para mantener al equipo en posiciones europeas.",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1543326727-cf6c39e8f84c?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-10 18:45",
                source = "Mundo Deportivo",
                originalLanguage = "スペイン語",
                isTranslated = true
            ),
            NewsArticle(
                id = "7",
                title = "バレンシアCF、ホームで劇的勝利",
                summary = "メスタージャでの一戦で90分にゴールを決め、2-1の勝利を収めました。",
                content = "メスタージャで行われた激戦は、バレンシアCFが劇的な勝利を収める結果となった。90分に決勝ゴールを決めたのは、今シーズン加入したばかりの新外国人選手だった。\n\n試合は前半から白熱した展開となり、両チームがゴールを狙い続けた。バレンシアが先制したものの、相手チームもすぐに同点ゴールを決め、1-1で後半に入った。\n\n後半は両チーム一歩も譲らない展開が続いたが、アディショナルタイムに入ってからバレンシアが決定的なチャンスを迎えた。コーナーキックからのこぼれ球を、新加入選手が冷静に押し込み劇的な勝利を演出した。\n\nこの勝利により、バレンシアは重要な勝ち点3を獲得し、リーグ戦での順位を大きく上げることとなった。ファンにとっても記憶に残る素晴らしい試合となった。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1551698618-1dfe5d97d256?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-09 21:15",
                source = "Super Deporte",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "8",
                title = "ビジャレアル、チャンピオンズリーグ圏内浮上",
                summary = "エスタディオ・デ・ラ・セラミカでの勝利によりCL圏内の4位に浮上しました。",
                content = "エスタディオ・デ・ラ・セラミカで行われた重要な一戦で、ビジャレアルが見事な勝利を収め、念願のチャンピオンズリーグ圏内である4位に浮上した。\n\n今シーズンのビジャレアルは一貫して安定したパフォーマンスを見せており、特に攻撃陣の連携が素晴らしい。今回の試合でも、前半から積極的な攻撃を仕掛け、相手守備陣を翻弄し続けた。\n\n監督のエメリは「チームが成長し続けている証拠だ。選手たちの努力と献身が今日の結果につながった」と満足の表情を見せた。\n\nこの順位により、来シーズンのチャンピオンズリーグ出場に大きく近づいたビジャレアル。残り試合も重要な戦いが続くが、この勢いを維持できれば目標達成も現実的になってきた。ファンにとっても夢のような展開が続いている。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1553778263-73a83bab9b0c?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1574629810360-7efbbe195018?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-08 20:30",
                source = "Villarreal CF公式",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "9",
                title = "ラス・パルマス、昇格後初勝利",
                summary = "1部復帰後苦戦していたラス・パルマスがようやく今季初勝利を挙げました。",
                content = "長い間勝利から遠ざかっていたラス・パルマスが、ついに今シーズン初勝利を挙げることができた。1部復帰後の厳しい戦いが続いていたが、チーム一丸となって掴んだ貴重な3ポイントだった。\n\n試合は序盤から激しい展開となり、両チームが積極的に攻撃を仕掛けた。ラス・パルマスは前半に先制ゴールを決め、後半も集中力を切らすことなく守り抜いた。\n\n勝利の瞬間、スタジアムは大きな歓声に包まれ、選手たちも感極まった表情を見せた。監督は「この勝利は選手、スタッフ、そしてファンみんなの努力の結果だ」と涙ながらにコメントした。\n\nこの勝利により、ラス・パルマスは残留争いにおいて重要な一歩を踏み出すことができた。チームの士気も大幅に向上し、今後の戦いに向けて大きな弾みとなることは間違いない。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1489944440615-453fc2b6a9a9?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?w=400&h=300&fit=crop",
                    "https://images.unsplash.com/photo-1544717684-b6a26df9e6b8?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-07 19:00",
                source = "Canarias7",
                originalLanguage = "日本語",
                isTranslated = false
            ),
            NewsArticle(
                id = "10",
                title = "ヘタフェ、堅守で勝ち点3を獲得",
                summary = "コリセウム・アルフォンソ・ペレスでヘタフェが1-0の勝利を収めました。",
                content = "コリセウム・アルフォンソ・ペレスで行われた試合で、ヘタフェが堅固な守備を武器に1-0の勝利を収めた。今シーズンのヘタフェの特徴でもある組織的な守備が今回も見事に機能した。\n\n試合は前半から両チームが慎重な戦術を選択し、なかなかゴールが生まれない膠着状態が続いた。しかし、後半に入ってからヘタフェが徐々にペースを掴み始めた。\n\n決勝ゴールは75分、セットプレーからのヘディングシュートだった。守備的な戦術で知られるヘタフェだが、この試合では攻撃でも良い形を作ることができていた。\n\n監督のボルダラスは「我々のスタイルを貫いた結果だ。選手たちは戦術を完璧に理解し、実行してくれた」と語った。この勝利により、ヘタフェは中位での安定した順位を確保することができた。",
                imageUrls = listOf(
                    "https://images.unsplash.com/photo-1544717684-b6a26df9e6b8?w=400&h=300&fit=crop"
                ),
                publishDateTime = "2024-03-06 17:30",
                source = "Getafe CF公式",
                originalLanguage = "日本語",
                isTranslated = false
            )
        )
    }
}