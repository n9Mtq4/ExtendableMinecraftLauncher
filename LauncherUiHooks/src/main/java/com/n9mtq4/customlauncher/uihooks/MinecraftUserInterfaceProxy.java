package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.customlauncher.uihooks.override.HookedSwingUserInterface;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.AdditionActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.AdditionListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.EnhancedProxy;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.MinecraftUserInterface;
import net.minecraft.launcher.SwingUserInterface;

import java.lang.reflect.Method;

/**
 * Created by will on 8/5/15 at 5:09 PM.<br>
 * @deprecated Use {@link HookedSwingUserInterface} and {@link SwingUserInterface} instead
 * @see HookedSwingUserInterface
 * @see SwingUserInterfaceHook
 */
@Deprecated
public final class MinecraftUserInterfaceProxy implements EnhancedProxy.EnhancedInvocationHandler, ObjectListener, AdditionListener {
	
	private BaseConsole baseConsole;
	
	@Override
	public final void onAddition(AdditionActionEvent e) {
		this.baseConsole = e.getBaseConsole();
	}
	
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("minecraftlauncher")) return;
		if (!(e.getObject() instanceof Launcher)) return;
		
		Launcher launcher = (Launcher) e.getObject();
		
		MinecraftUserInterface userInterface = EnhancedProxy.newInstance((SwingUserInterface) launcher.getUserInterface(), this);
		ReflectionHelper.setObject(userInterface, "userInterface", launcher);
		
	}
	
	@Override
	public final Object invoke(Object o, Object o1, Method method, Object[] objects) throws Throwable {
		baseConsole.push(new Object[]{o, o1, method, objects}, method.getName() + " called");
		return EnhancedProxy.callChild(o, method, objects);
//		return null;
	}
	
}
