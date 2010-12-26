#import "LocationMarker.h"

@implementation LocationMarker

@synthesize coordinate;
@synthesize point;

-(id) initWithPoint: (TrailPoint*) point {
    
	self = [super init];
	if (self != nil) {
		coordinate.latitude = point.latitude;
		coordinate.longitude = point.longitude;
		self.point = point;
	}
	return self;
}

#pragma mark -
#pragma mark - Description methods

- (NSString *)subtitle {
    
	return self.point.description;
}

- (NSString *)title {
    
	return self.point.name;
}

#pragma mark -
#pragma mark - Dealloc

- (void) dealloc {
    
	[point release];
	[super dealloc];
}

@end
