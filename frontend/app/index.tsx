import { View, Text, StyleSheet, ImageBackground, TouchableOpacity } from 'react-native';
import { images } from '../assets/images';
import { useRouter } from 'expo-router';

export const options = {
  headerShown: false,
};
export default function HomeScreen() {
  const router = useRouter();

  return (
    <ImageBackground
      source={images.volleyball}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.overlay}>
        <Text style={styles.title}>🏐 Volleyball Manager</Text>

        <TouchableOpacity
          style={styles.button}
          onPress={() => router.push('/(tabs)/team')}
        >
          <Text style={styles.buttonText}>Mein Team anzeigen</Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  overlay: {
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
    padding: 30,
    borderRadius: 12,
    alignItems: 'center',
    gap: 20,
  },
  title: {
    fontSize: 28,
    color: '#fff',
    fontWeight: 'bold',
    marginBottom: 20,
  },
  button: {
    backgroundColor: '#ff9500',
    paddingVertical: 12,
    paddingHorizontal: 24,
    borderRadius: 8,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
  },
});
