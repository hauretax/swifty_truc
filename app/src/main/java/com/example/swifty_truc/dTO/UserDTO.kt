import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("usual_full_name") val usualFullName: String?,
    @SerializedName("usual_first_name") val usualFirstName: String?,
    @SerializedName("url") val url: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("displayname") val displayName: String,
    @SerializedName("kind") val kind: String,
    @SerializedName("image") val image: Image,
    @SerializedName("staff?") val staff: Boolean,
    @SerializedName("correction_point") val correctionPoint: Int,
    @SerializedName("pool_month") val poolMonth: String,
    @SerializedName("pool_year") val poolYear: String,
    @SerializedName("location") val location: String?,
    @SerializedName("wallet") val wallet: Int,
    @SerializedName("anonymize_date") val anonymizeDate: String,
    @SerializedName("data_erasure_date") val dataErasureDate: String?,
    @SerializedName("alumni?") val alumni: Boolean,
    @SerializedName("active?") val active: Boolean,
    @SerializedName("groups") val groups: List<Any>,
    @SerializedName("cursus_users") val cursusUsers: List<CursusUser>,
    @SerializedName("projects_users") val projectsUsers: List<ProjectUser>,
    @SerializedName("languages_users") val languagesUsers: List<LanguageUser>,
    @SerializedName("achievements") val achievements: List<Any>,
    @SerializedName("titles") val titles: List<Any>,
    @SerializedName("titles_users") val titlesUsers: List<Any>,
    @SerializedName("partnerships") val partnerships: List<Any>,
    @SerializedName("patroned") val patroned: List<Patron>,
    @SerializedName("patroning") val patroning: List<Any>,
    @SerializedName("expertises_users") val expertisesUsers: List<ExpertiseUser>,
    @SerializedName("roles") val roles: List<Any>,
    @SerializedName("campus") val campus: List<Campus>,
    @SerializedName("campus_users") val campusUsers: List<CampusUser>
)

data class ProjectUser(
    @SerializedName("id") val id: Int,
    @SerializedName("occurrence") val occurrence: Int,
    @SerializedName("final_mark") val finalMark: Int?,
    @SerializedName("status") val status: String,
    @SerializedName("validated?") val validated: Boolean?,
    @SerializedName("current_team_id") val currentTeamId: Int?,
    @SerializedName("project") val project: Project,
    @SerializedName("cursus_ids") val cursusIds: List<Int>,
    @SerializedName("marked_at") val markedAt: String?,
    @SerializedName("marked") val marked: Boolean,
    @SerializedName("retriable_at") val retriableAt: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class Project(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("parent_id") val parentId: Int?
)

data class Image(
    @SerializedName("link") val link: String,
    @SerializedName("versions") val versions: ImageVersions
)

data class ImageVersions(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("small") val small: String,
    @SerializedName("micro") val micro: String
)

data class CursusUser(
    @SerializedName("id") val id: Int,
    @SerializedName("begin_at") val beginAt: String,
    @SerializedName("end_at") val endAt: String?,
    @SerializedName("grade") val grade: String?,
    @SerializedName("level") val level: Double,
    @SerializedName("skills") val skills: List<Skill>,
    @SerializedName("cursus_id") val cursusId: Int,
    @SerializedName("has_coalition") val hasCoalition: Boolean,
    @SerializedName("user") val user: CursusUserDetail,
    @SerializedName("cursus") val cursus: Cursus
)

data class Skill(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Double,
)

data class CursusUserDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("url") val url: String
)

data class Cursus(
    @SerializedName("id") val id: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
)

data class LanguageUser(
    @SerializedName("id") val id: Int,
    @SerializedName("language_id") val languageId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("position") val position: Int,
    @SerializedName("created_at") val createdAt: String
)

data class Patron(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("godfather_id") val godfatherId: Int,
    @SerializedName("ongoing") val ongoing: Boolean,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class ExpertiseUser(
    @SerializedName("id") val id: Int,
    @SerializedName("expertise_id") val expertiseId: Int,
    @SerializedName("interested") val interested: Boolean,
    @SerializedName("value") val value: Int,
    @SerializedName("contact_me") val contactMe: Boolean,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("user_id") val userId: Int
)

data class Campus(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("time_zone") val timeZone: String,
    @SerializedName("language") val language: LanguageDetail,
    @SerializedName("users_count") val usersCount: Int,
    @SerializedName("vogsphere_id") val vogsphereId: Int
)

data class LanguageDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("identifier") val identifier: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class CampusUser(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("campus_id") val campusId: Int,
    @SerializedName("is_primary") val isPrimary: Boolean
)
