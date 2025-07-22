package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
)

@Composable
fun LaLigaNewsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MaterialTheme.typography.copy(
            displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = FontFamily.Default),
            displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = FontFamily.Default),
            displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = FontFamily.Default),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Default),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Default),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
            titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
            titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = FontFamily.Default),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Default),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Default),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Default),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = FontFamily.Default),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = FontFamily.Default),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Default)
        ),
        content = content
    )
}