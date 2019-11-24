package com.averoes.footballapp

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.averoes.footballapp.mvp.model.event.EventsItem
import com.averoes.footballapp.mvp.view.activity.TeamDetail
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamAdapter(private val context: Context, var teamItems: List<EventsItem> = listOf()) : RecyclerView.Adapter<TeamAdapter.Holder>() {


    class MatchItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {

                lparams {
                    width = matchParent
                    height = wrapContent
                }
                padding = dip(8)
                orientation = LinearLayout.HORIZONTAL

                cardView {

                    radius = dip(8).toFloat()

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL
                        orientation = LinearLayout.VERTICAL
                        textView {
                            id = R.id.event_date
                            textColor = resources.getColor(R.color.colorAccent)
                            textSize = 15f //sp
                        }.lparams {
                            gravity = Gravity.CENTER
                        }
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            textView {
                                id = R.id.home_team
                                gravity = Gravity.CENTER
                                setPaddingRelative(dip(5), paddingTop, paddingEnd, paddingBottom)
                                textSize = 17f //sp
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(width = matchParent) {
                                weight = 1f
                            }
                            linearLayout {
                                textView {
                                    id = R.id.home_team_score
                                    gravity = Gravity.END
                                    text = "0"
                                    textSize = 17f //sp
                                }.lparams {
                                    weight = 1f
                                }
                                textView {
                                    gravity = Gravity.CENTER
                                    text = "VS"
                                    textSize = 17f //sp
                                }.lparams {
                                    weight = 1f
                                }
                                textView {
                                    id = R.id.away_team_score
                                    gravity = Gravity.START
                                    text = "0"
                                    textSize = 17f //sp
                                }.lparams {
                                    weight = 1f
                                }
                            }.lparams(width = matchParent) {
                                weight = 1f
                            }
                            textView {
                                id = R.id.away_team
                                gravity = Gravity.CENTER
                                leftPadding = dip(5)
                                textSize = 17f //sp
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(width = matchParent) {
                                weight = 1f
                            }
                        }.lparams(width = matchParent)
                    }.lparams(width = matchParent, height = dip(100))
                }.lparams(width = matchParent) {
                    padding = dip(5)

                }


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder =
        Holder(MatchItemUI().createView(AnkoContext.create(context, parent)))

    override fun getItemCount(): Int = teamItems.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(teamItems[position])
    }


    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val teamName = itemView.find<TextView>(R.id.home_team)
        private val teamAway = itemView.find<TextView>(R.id.away_team)
        private val homeScore = itemView.find<TextView>(R.id.home_team_score)
        private val awayScore = itemView.find<TextView>(R.id.away_team_score)
        private val dateEvent = itemView.find<TextView>(R.id.event_date)



        fun bindItem(item: EventsItem) {

            if (item.strHomeTeam != null)teamName.text = item.strHomeTeam else teamName.text = "-"
            if (item.strAwayTeam != null)teamAway.text = item.strAwayTeam else teamAway.text = "-"
            if (item.intHomeScore != null)homeScore.text = item.intHomeScore else homeScore.text = "-"
            if (item.intAwayScore != null)awayScore.text = item.intAwayScore else awayScore.text = "-"
            dateEvent.text = item.dateEvent


            itemView.setOnClickListener {
                itemView.context.startActivity(itemView.context.intentFor<TeamDetail>("detail" to item).newTask())
            }
        }
    }
}