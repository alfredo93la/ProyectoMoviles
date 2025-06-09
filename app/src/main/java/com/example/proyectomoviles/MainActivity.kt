package com.example.proyectomoviles

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa vistas
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        toolbar = findViewById(R.id.toolbar)

        // Establecer Toolbar como ActionBar
        setSupportActionBar(toolbar)

        // Toggle para abrir/cerrar drawer con icono de hamburguesa
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Fragmento por defecto
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment())
                .commit()
        }

        // Navegación bottom
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_inicio -> {
                    replaceFragment(InicioFragment())
                    true
                }
                R.id.nav_consultar -> {
                    replaceFragment(ConsultarFragment())
                    true
                }
                R.id.nav_salir -> {
                    finish() // cerrar la app
                    true
                }
                else -> false
            }
        }

        // Navegación drawer
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.drawer_add_event -> replaceFragment(AnadirEventoFragment())
                R.id.drawer_consult -> replaceFragment(ConsultarFragment())
                R.id.drawer_backup -> replaceFragment(RespaldoFragment())
                R.id.drawer_restore -> replaceFragment(RestaurarFragment())
                R.id.drawer_about -> replaceFragment(AcercaDeFragment())
                R.id.drawer_exit -> finish()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}